package cardealer.services.impl;

import cardealer.dto.binding.CustomerCreateBindingModel;
import cardealer.dto.views.CustomerView;
import cardealer.dto.views.CustomerWithSalesView;
import cardealer.dto.views.SaleView;
import cardealer.entities.Customer;
import cardealer.parsers.ModelParser;
import cardealer.repositories.CustomerRepository;
import cardealer.services.api.CustomerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final ModelParser mapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(ModelParser mapper, CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomers(CustomerCreateBindingModel[] models) {
        for (CustomerCreateBindingModel model : models) {
            Customer customer = mapper.convert(model, Customer.class);
            customerRepository.save(customer);
        }
    }

    @Override
    public List<CustomerView> getOrderedCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> new CustomerView(c.getId(), c.getName(), c.getBirthDate(), c.getYoungDriver(),
                        c.getSales().stream().map(s -> new SaleView(s.getId())).collect(Collectors.toList())))
                .sorted(Comparator.comparing(CustomerView::getDate)
                        .thenComparing(CustomerView::getYoungDriver, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerWithSalesView> getCustomersWithSales() {
        return customerRepository.findCustomersWithSales()
                .stream()
                .map(c -> new CustomerWithSalesView(c.getName(), c.getSales().size(),
                        c.getSales()
                                .stream()
                                .mapToDouble(s -> s.getCar().getParts()
                                        .stream()
                                        .mapToDouble(p -> p.getPrice().doubleValue())
                                        .sum() * (1 - s.getDiscount()))
                                .sum()))
                .sorted(Comparator.comparing(CustomerWithSalesView::getSpentMoney, Comparator.reverseOrder())
                        .thenComparing(CustomerWithSalesView::getBoughtCars, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
