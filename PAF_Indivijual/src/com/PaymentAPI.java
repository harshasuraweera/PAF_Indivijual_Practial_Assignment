package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PaymentModel;


@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    
	PaymentModel paymentModel =  new PaymentModel();
   
    public PaymentAPI() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = paymentModel.addToCart(
						request.getParameter("loggedUsername"),
						request.getParameter("productId"),
						request.getParameter("productName"),
						request.getParameter("shortDescription"),
						request.getParameter("quantity"),
						request.getParameter("productPrice"));
				

		response.getWriter().write(result);
	}
	
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}

		} catch (Exception e) {

		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

//		String result = customerObj.updateCustomer(param.get("hidcustomerIDSave").toString(),
//				param.get("customerName").toString().toString().replace("+", " "),
//				param.get("customerEmail").toString().toString().replace("%40", "@"),
//				param.get("customerType").toString().toString().replace("+", " "),
//				param.get("customerContact").toString());
//
	//	response.getWriter().write(result);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String result = paymentModel.deleteFromCart(param.get("cartId").toString());

		response.getWriter().write(result);
	}
	

}
