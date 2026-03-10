package co.istad.suiii.fswd.sbapp.Bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton")
@Scope("prototype")
// this create MyAnnotationBean bean = new MyAnnotationBean()
@Getter
@NoArgsConstructor
public class MyAnnotationBean {
    String name;
}
