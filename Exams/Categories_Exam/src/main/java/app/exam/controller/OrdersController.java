package app.exam.controller;

import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class OrdersController {
    private Parser parser;
    private Parser jsonParser;
    private OrderService orderService;

    public OrdersController(@Qualifier("XMLParser") Parser parser,@Qualifier("JSONParser") Parser jsonParser, OrderService orderService) {
        this.parser = parser;
        this.jsonParser = jsonParser;
        this.orderService = orderService;
    }

    public String importDataFromXML(String xmlContent) {
        OrderWrapperXMLImportDTO dtos = null;
        StringBuilder builder = new StringBuilder();
        try {
             dtos = this.parser.read(OrderWrapperXMLImportDTO.class, xmlContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        for (OrderXMLImportDTO orderXMLImportDTO : dtos.getOrders()) {
            try {
                this.orderService.create(orderXMLImportDTO);
                builder.append(String.format("Order for %s on %s added.",
                        orderXMLImportDTO.getCustomer(), orderXMLImportDTO.getDate()));
                builder.append(System.lineSeparator());

            } catch (Exception e) {
                builder.append("Error: Invalid data.");
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        try {
           return this.jsonParser.write(this.orderService.
                    exportOrdersByEmployeeAndOrderType(employeeName,orderType));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
