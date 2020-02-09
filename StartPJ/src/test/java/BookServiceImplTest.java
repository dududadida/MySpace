import com.dududadida.dto.AppointExecution;
import com.dududadida.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @FileRelativePath StartPJ\src\test\java\BookServiceImplTest.java
 * @Author wangzheng
 * @Date 2020/1/10 9:39
 * @Description 业务测试
 */

public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 12345678910L;
        AppointExecution execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }

}