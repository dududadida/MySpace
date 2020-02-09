/**
 * @PackageName PACKAGE_NAME
 * @Author wangzheng
 * @Date 2020/1/13 12:16
 * @Description
 */
import com.dududadida.dao.AppointmentDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.dududadida.entity.Appointment;

public class AppointmentDaoTest extends BaseTest {

    @Autowired
    private AppointmentDao appointmentDao;

    @Test
    public void testInsertAppointment() throws Exception {
        long bookId = 1000;
        long studentId = 12345678910L;
        int insert = appointmentDao.insertAppointment(bookId, studentId);
        System.out.println("insert=" + insert);
    }

    @Test
    public void testQueryByKeyWithBook() throws Exception {
        long bookId = 1000;
        long studentId = 12345678910L;
        Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
        System.out.println(appointment);
        System.out.println(appointment.getBook());
    }

}