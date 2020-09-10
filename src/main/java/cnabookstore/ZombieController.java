package cnabookstore.config.external;

import cnabookstore.config.external.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ZombieController {

    private boolean flag;

    @Autowired
    private BookService bookService;

    public ZombieController(){
        flag = true;
    }

    @GetMapping({"/isHealthy"})
    public void zombie2() throws Exception {
        if (flag)
            return;
        else
            throw new Exception("zombie.....");
    }

    @GetMapping({"/makeZombie"})
    public void getStockInputs() {

        flag = false;

    }

    @GetMapping({"/orderService/verifyBook/{bookId}"})
    public @ResponseBody String verifyBook(@PathVariable("bookId") Long bookId) {

        bookService.queryBook(bookId);
        return "Success...............";

    }

}
