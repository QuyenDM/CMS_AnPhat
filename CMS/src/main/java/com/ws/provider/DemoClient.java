package com.ws.provider;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.ws.provider;
//
///**
// *
// * @author TamdX
// */
//import com.viettel.gnoc.incident.dto.TroubleFileDTO;
//import com.viettel.gnoc.incident.service.TroubleFileService;
//import java.util.List;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//
//public class DemoClient {
//
//    public static void main(String[] args) {
//
//        String serviceUrl = "http://10.58.71.216:8086/ProblemGNOC/troubleFileService?wsdl";
//
//        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//
//        factory.setServiceClass(TroubleFileService.class);
//
//        factory.setAddress(serviceUrl);
//        
//        
//
//        TroubleFileService bookService = (TroubleFileService) factory.create();
//        TroubleFileDTO dto = new TroubleFileDTO();
//        dto.setTroubleId("114");
//        List<TroubleFileDTO> ret = bookService.getListTroubleFileDTO(dto, 0, Integer.MAX_VALUE, "asc", "");
//        System.out.println(ret);
//
//        //insert book
////        BookVO bookVO = new BookVO();
////
////        bookVO.setAuthor("Issac Asimov");
////
////        bookVO.setBookName("Foundation and Earth");
////
//// 
////
////        String result = bookService.insertBook(bookVO);
////
//// 
////
////        System.out.println("result : " + result);
////
//// 
////
////        bookVO = new BookVO();
////
////        bookVO.setAuthor("Issac Asimov");
////
////        bookVO.setBookName("Foundation and Empire");
////
//// 
////
////        result = bookService.insertBook(bookVO);
////
//// 
////
////        System.out.println("result : " + result);
////
//// 
////
////        bookVO = new BookVO();
////
////        bookVO.setAuthor("Arthur C Clarke");
////
////        bookVO.setBookName("Rama Revealed");
////
//// 
////
////        result = bookService.insertBook(bookVO);
////
//// 
////
////        System.out.println("result : " + result);
////
//// 
////
////        //retrieve book
////
//// 
////
////        bookVO = bookService.getBook("Foundation and Earth");
////
////        System.out.println("book name : " + bookVO.getBookName());
////
////        System.out.println("book author : " + bookVO.getAuthor());
//    }
//
//}
