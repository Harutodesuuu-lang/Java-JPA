package co.istad.suiii.fswd.sbapp.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyBeanController {
    //1. Declare dependency
    private final MyAnnotationBean bean; // Required dependency because it has final
    private MyAnnotationBean bean2; //optional dependency
    //2. Inject dependency (constructor-based)
    public MyBeanController(MyAnnotationBean bean) {
        this.bean = bean;
    }
    //3. Inject dependency (setter-based)
@Autowired
    public void setBean2(MyAnnotationBean bean2) {
        this.bean2 = bean2;
    }

    @GetMapping("/bean")
    public List<MyAnnotationBean> getBean() {
        System.out.println("Bean: "+ bean);
        System.out.println("Bean2: "+ bean2);
        bean.name = "Ronaldo";
        bean2.name = "Messi";
        return List.of(bean,bean2);
    }
}
