package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderItemRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private ModelParser modelParser;
    private Validator validator;
    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private ItemsRepository itemsRepository;
    private OrderItemRepository orderItemRepository;

    public OrderServiceImpl(ModelParser modelParser, Validator validator, OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemsRepository itemsRepository, OrderItemRepository orderItemRepository) {
        this.modelParser = modelParser;
        this.validator = validator;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemsRepository = itemsRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        if(validator.validate(dto).size() != 0) {
            throw new IllegalArgumentException();
        }

            Employee employee = this.employeeRepository.findByName(dto.getEmployee());
            if(employee == null) {
                throw new IllegalArgumentException();
            }
        List<OrderItem> orderedItems = new LinkedList<>();
            Item item = null;
        for (OrderItemXMLImportDTO orderItemXMLImportDTO : dto.getItems()) {
            item  = this.itemsRepository.findByName(orderItemXMLImportDTO.getName());
            if(item == null) {
                throw new IllegalArgumentException();
            } else {
                OrderItem orderItem =
                        new OrderItem(item, orderItemXMLImportDTO.getQuantity());
                orderedItems.add(orderItem);
            }
        }
            BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderedItems) {
            totalPrice = totalPrice.add(orderItem.getItem().getPrice());
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = df.parse(dto.getDate());
        Order order = new Order(dto.getCustomer(), date, OrderType.valueOf(dto.getType()),
                totalPrice, employee, orderedItems);

        employee.getOrders().add(order);

        for (OrderItem orderedItem : orderedItems) {
            orderedItem.setItem(item);
            orderedItem.setOrder(order);
        }

        this.employeeRepository.save(employee);
        this.orderRepository.save(order);
        this.orderItemRepository.save(orderedItems);
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        Employee employee = this.employeeRepository.findByName(employeeName);

        List<Order> ordersWithGivenOrderType = employee
                .getOrders()
                .stream()
                .filter(x -> x.getOrderType().name().equals(orderType))
                .sorted(Comparator.comparing(Order::getTotalPrice,
                        Comparator.reverseOrder())
                        .thenComparing(Comparator
                                .comparing((Order y) -> y.getOrderedItems().size()
                        ,Comparator.reverseOrder())))
                .collect(Collectors.toList());

        List<OrderJSONExportDTO> dtoOrdersList = new LinkedList<>();
        List<ItemJSONExportDTO> dtoItemsList = new LinkedList<>();

        for (Order order : ordersWithGivenOrderType) {
            List<OrderItem> orderedItems = order.getOrderedItems()
                    .stream().sorted(Comparator.comparing(OrderItem::getId))
                    .collect(Collectors.toList());
            for (OrderItem orderItem : orderedItems) {
                ItemJSONExportDTO itemDto =
                        new ItemJSONExportDTO(orderItem.getItem().getName(),
                        orderItem.getItem().getPrice(), orderItem.getQuantitiy());
                dtoItemsList.add(itemDto);
            }
            OrderJSONExportDTO orderDto =
                    new OrderJSONExportDTO(order.getCustomer(), dtoItemsList);
            dtoOrdersList.add(orderDto);
        }

        return new EmployeeOrdersJSONExportDTO(employee.getName(), dtoOrdersList);
    }
}
