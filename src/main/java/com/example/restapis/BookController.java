package com.example.restapis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);
    private static HashMap<Integer,Book> bookHashMap = new HashMap<Integer,Book>();

    // To fetch book id from client and return the details of the particular book
    // ? @RequestParam / @QueryParam comes from different dependency jax-rs (old dependency) -> They can be used both same time but don't use it.


    @PostMapping("/book")
    public void insertBook(@RequestBody Book book){

        logger.info("Book coming from the request - {}", book);
        bookHashMap.put(book.getId(),book);

        // whatever frontend would be sending this would be converted into Book object.

    }

    //No Two API should be similar
    /*
        Same path + same HTTP method.

        Automatically when you search in a browser it is a get request.
        secure parameters can be send in RequestHeader & this might not be related to use case & we don't want it to send it in body
        because it is not primary information & is a secondary information.
     */

    @GetMapping("/book")
//  @RequestMapping(value = "/book", method = {RequestMethod.GET,RequestMethod.POST})
    public Book getBook(@RequestParam(value = "bid",
            required = false, defaultValue = "1") Integer bookId){


        /*
         public Book getBook(@RequestParam("bid") int bookId){}
         http://localhost:8080/book?bid=Hello
         MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'int'
         For input string: "Hello"!! (If we pass String from browser but type is int)
         */

        logger.info("bID - {}",bookId);
        return bookHashMap.get(bookId);
    }


    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId){
        logger.info("bID - {}",bookId);
        return bookHashMap.get(bookId);

    }


    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return bookHashMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    // FE and BE -----> CONTRACT
    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book, @RequestParam("id") int bookId){
    bookHashMap.put(bookId, book);
    return bookHashMap.get(bookId);
    }

    @DeleteMapping("/book")
    public Book deleteBook(@RequestParam("id") int id) {
        return bookHashMap.remove(id);
        //[] book/all
    }

    @GetMapping("/dummy/{id}")
    public String dummy(@PathVariable("id") int id , @RequestParam("param") int param){
        //http://localhost:8080/dummy/001?param=007
        logger.info("id - {} , param - {}", id , param);
        return "hello";
    }

    @GetMapping("/esps/{espId}/corporates/{corporateId}/companies/{companyId}")
    public String dummy(@PathVariable("espId") int id ,@PathVariable("corporateId") int id1,@PathVariable("companyId") int id2){
        // take as many, as path variable you want
        return "hello";
    }






    /*

    [[curl is nothing but an agent used to hit your http apis]]
    curl --version
    Request Headers - F.E -> B.E
    Response Headers - B.E -> F.E
    curl -XGET "localhost:8080/book/all" -V
                [ P A T H ]              [-V tp give more knowledge about protocol]


    curl -XPOST 'localhost:8080/insert_book?sampleParam=hello' -H 'Content-type: application/json'
            -d '{"id":1 , "name":"Java", "cost": 200, "author":"ronaldo"}' - v
         [ -H sending data in header, -d data, type of data - json]


         Internally postman is also using curl to hit a request,
         even internally your browser also uses that.
    */




}
