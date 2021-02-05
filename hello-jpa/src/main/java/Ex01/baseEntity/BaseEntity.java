package Ex01.baseEntity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 2021-02-03
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
@MappedSuperclass
@Data
public class BaseEntity {
    private String createUser;
    private LocalDateTime createDate;
    private String lastModifyUser;
    private LocalDateTime lastModifyDate;
}
