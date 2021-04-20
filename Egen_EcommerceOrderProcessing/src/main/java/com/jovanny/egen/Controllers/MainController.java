package com.jovanny.egen.Controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.jovanny.egen.DAO.Egen_DB;
import com.jovanny.egen.Models.*;

//TODO: add links to returning objects
//TODO: Sort,pagination for performance
//TODO: Add status codes
//TODO: Error handling
//TODO: Add write logs after each request and error logs if anything happen to any order
@RestController
public class MainController {

	@Autowired
	Egen_DB egen_db;
	
	public MainController() {

	}
	
	public MainController(Egen_DB e) {
		this.egen_db = e;
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String index() {
    	return "test method";
    }
	
	
    @RequestMapping(value = { "/api/order/getAllOrders" }, method = RequestMethod.GET)
    public List<Order_Wrapper> getAllOrders() {
    	ArrayList<Order_Wrapper> ow = new ArrayList<Order_Wrapper>();
    	  egen_db.orders.findAll().forEach(
    			  e -> {
    				 ow.add(getOrder(e.getCustomer_id())); 
    			  });
    	 return ow;
    }
    

    @RequestMapping(value = { "/api/order/getOrder" }, method = RequestMethod.GET)
    public Order_Wrapper getOrder(@RequestParam(required = true)long order_id) {
    	
    	Order_Wrapper ow = new Order_Wrapper();
    	
    	@SuppressWarnings("deprecation")
		Long order_id_long = Long.valueOf(order_id);
    	
    	//get order by order id
    	Stream<Order> x = egen_db.orders.getByOrderID(order_id_long).stream();
    	x.forEach(e -> ow.setOrder(e));
    	
    	//get customer by customer id from order
    	Stream<Customer> c = egen_db.customers.getByCustomerID(ow.getOrder().getId()).stream();
    	c.forEach(e -> ow.setCustomer(e));
    	
    	//get payments by order id
    	ArrayList<Payment_info> li = new ArrayList<Payment_info>();
    	Stream<Payment_info> pi = egen_db.payment_info.getPaymentsByOrderID(ow.getOrder().getId()).stream();
    	pi.forEach(e -> li.add(e));
    	ow.setPayment_info(li);
    	
    	//get all items by order id
    	ArrayList<Item> i = new ArrayList<Item>();
    	Stream<Order_Details> op = egen_db.or_details.getByOrderID(ow.getOrder().getId()).stream();
    	op.forEach(e ->
    	{
    		var its = egen_db.items.getByItemID(e.item_id).stream();
    		its.forEach(
    				r -> i.add(r)); 
    	});
    	ow.setItems(i);
    	
    	return ow;
    }

    //assume all objects are build correctly in client side
    @PostMapping("/api/order/orderBulk")
    Order_Wrapper addOrdersInBulk(@RequestBody List<Order_Wrapper> orders) {
    	
    	//get all orders objects into a list
    	ArrayList<Order> os = new ArrayList<Order>();    	
    	orders.forEach(
    			e ->{
    				os.add( e.getOrder() );
    			});
    	
    	ArrayList<Payment_info> pi = new ArrayList<Payment_info>();
    	orders.forEach(
    			e ->{
    				pi.addAll(e.getPayment_info());
    			});
    	
    	
    	ArrayList<Order_Details> or_dt = new ArrayList<Order_Details>();
    	orders.forEach(
    			order ->{
    				order.getItems().forEach(
    						item ->{
					    	  Order_Details od = new Order_Details();
					    	  od.item_id = item.getItem_id();
					    	  od.order_id = order.getOrder().getId();
					    	  od.quantity = 1;
					    	  od.subtotal = item.getItem_price();
					    	  od.tax = 15;
					    	  
					    	  or_dt.add(od);
    						});
    			});
    	
    	//why check for null or empty after performing operations on objects above
    	if(orders != null && !orders.isEmpty()) {
            
    		egen_db.orders.saveAll(os);
    		
    		egen_db.or_details.saveAll(or_dt);
    		
    		egen_db.payment_info.saveAll(pi);
        } else {
            
        }

    	
    	return orders.get(0);
    }
    //Assume customer always exists
    //Assume items should always exist and are in stock
    //create order
    //add a new entry to order details
    //add entries for payment
    @PostMapping("/api/order/newOrder")
    Order_Wrapper newOrder(@RequestBody Order_Wrapper ow) {
    	
      ow.setOrder(egen_db.orders.save(ow.getOrder()));
      
      ow.getItems().forEach(e -> {
    	  Order_Details od = new Order_Details();
    	  od.item_id = e.getItem_id();
    	  od.order_id = ow.getOrder().getId();
    	  od.quantity = 1;
    	  od.subtotal = e.getItem_price();
    	  od.tax = 15;
    	  egen_db.or_details.save(od);
      });
      
      //in real world apps you would call a pay processor before creating a new entry for order
      ow.getPayment_info().forEach(e -> {    	  
    	  egen_db.payment_info.save(e);
      });
      
      return getOrder(ow.getOrder().getId());
    }

    //Same logic as before get each order_wrapper obj and then get order, payments
    //and items of each single order into a queue type of data structure(list) 
    //then do a bulk update using jpa saveAll method
    @PutMapping("/api/order/updateOrderBulk")
    Order_Wrapper updateOrderInBulk(@RequestBody List<Order_Wrapper> o) {
    	
    	//modified at timestamp
    	Date date = new Date(); 
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	formatter.format(date);
    	
    	egen_db.orders.findAllById(null);
    	
    	return o.get(0);
    }
    //update order record
    //update or add to order details
    //update or add to payments
    @PutMapping("/api/order/updateOrder")
    Order_Wrapper updateOrder(@RequestBody Order_Wrapper o, Long order_id) {
      
    	//modified at timestamp
    	Date date = new Date(); 
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	System.out.println(formatter.format(date));
    	
    	//update order
    	Stream<Order> or = egen_db.orders.getByOrderID(order_id).stream();
    	or.forEach( order ->{
    		order.setOrder_shipping_address(o.getOrder().getOrder_shipping_address());
    		order.setOrder_shipping_city(o.getOrder().getOrder_shipping_city());
    		order.setOrder_shipping_state(o.getOrder().getOrder_shipping_state());
    		order.setOrder_shipping_zip(o.getOrder().getOrder_shipping_zip());
    		order.setOrder_status(o.getOrder().getOrder_status());
    		order.setDelivery_method(o.getOrder().getDelivery_method());
    		order.setModified_at(formatter.format(date));
    		
    		egen_db.orders.save(order);
    	});
    	
    	//update order details   
    	//Use incoming objects to check against db
    	ArrayList<Item> itms = new ArrayList<Item>();
    	o.getItems().forEach(
    			item ->{
    				//check if item is already part of the order
    				var itemsInOrderWithSameID = egen_db.or_details.getItemsByOrderIdandItemId(order_id, new Long(item.getItem_id()));
    				//if empty item has not been added to order yet
    				if(itemsInOrderWithSameID.isEmpty()) {
						//create order detail based on this item
		    			 Order_Details ord_d = new Order_Details();
		    			 ord_d.item_id = item.getItem_id();
		    			 ord_d.order_id = o.getOrder().getId();
		    			 ord_d.quantity = 1;
		    			 ord_d.subtotal = item.getItem_price();
		    			 ord_d.tax = 15;
		    	    	 egen_db.or_details.save(ord_d);
    				}
    				    
    		    	//if not empty but multiple of the same item in the same order
    				//compare size of lists if incoming list is bigger add the difference as new entries of the same item    				
    				o.getItems().forEach(
    						i ->{
    							if (i.getItem_id() == item.getItem_id()) {
									itms.add(item);
								}
    						});
    				addItems(o.getOrder(),item, itms.size()-itemsInOrderWithSameID.size());
    			});
    	
    	//update payments
    	ArrayList<Payment_info> pymnts = new ArrayList<Payment_info>();
    	o.getPayment_info().forEach(
    			pay ->{
    				//check if item is already part of the order
    				var paymentsInOrderWithSameID = egen_db.payment_info.getPaymentsByOrderIdandItemId(order_id, new Long(pay.getPayment_id()));
    				//if empty item has not been added to order yet
    				if(paymentsInOrderWithSameID.isEmpty()) {
						egen_db.payment_info.save(pay);
    				}
    				
    				o.getPayment_info().forEach(
    						p ->{
    							if (p.getPayment_id() == pay.getPayment_id()) {
									pymnts.add(pay);
								}
    						});
    				addPayments(o.getOrder(), pay, pymnts.size()-paymentsInOrderWithSameID.size());
    			});
    	
    	
    	return getOrder(order_id);
    }
    
    private boolean addItems(Order o,Item i, int index){
    	
		for (int j = 0; j < index; j++) {
			//create order detail based on this item
			 Order_Details ord_d = new Order_Details();
			 ord_d.item_id = i.getItem_id();
			 ord_d.order_id = o.getId();
			 ord_d.quantity = 1;
			 ord_d.subtotal = i.getItem_price();
			 ord_d.tax = 15;
	    	 egen_db.or_details.save(ord_d);		
		}
    	
    	return true;
    }
    
    private boolean addPayments(Order o, Payment_info p, int index) {
    	for (int j = 0; j < index; j++) {
			p.setOrder_id(o.getId());
	    	egen_db.payment_info.save(p);		
		}
    	return true;
    }

    //Deletion order: order_details, order, payments
    @DeleteMapping("/api/order/{id}")
    void deleteOrder(@PathVariable int order_id) {
    	
    	//find all items that belong to the order and delete them    	
    	Stream<Order_Details> op = egen_db.or_details.getByOrderID(order_id).stream();
    	op.forEach(e ->
    	{
    		egen_db.or_details.deleteById(new Long(e.order_details_id));
    	});
    	
    	//find all the payments made to this order and delete them
    	Stream<Payment_info> pi = egen_db.payment_info.getPaymentsByOrderID(order_id).stream();
    	pi.forEach(e -> egen_db.payment_info.deleteById(e.getPayment_id()));
    	
    	//finally delete the order
    	egen_db.orders.deleteById((long)order_id);
    }
    
    
    
    //From end clients will need apis like below for tables like delivery method, payment type, status
    @RequestMapping(value = { "/api/status/getStatus" }, method = RequestMethod.GET)
    public Optional<Status> getStatus(String name) {
    	
    	return egen_db.status.getByName(name);
    }
    
    @RequestMapping(value = { "/api/status/getAllStatus" }, method = RequestMethod.GET)
    public List<Status> getAllStatus(){
    	return egen_db.status.findAll();
    }
    
    //Testing bulk operations: Insert into status table
    @RequestMapping(value = { "/api/status/createinbulk" }, method = RequestMethod.POST)
    public String addStatus(@RequestBody List<Status> s) {
        if(s != null && !s.isEmpty()) {
            egen_db.status.saveAll(s);
            return String.format("Added %d people.", s.size());
        } else {
            return "NO BODY";
        }
    }
}
