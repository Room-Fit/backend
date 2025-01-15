    package com.roomfit.be.user.domain;

    import com.roomfit.be.global.entity.BaseEntity;
    import com.roomfit.be.message.domain.Message;
    import com.roomfit.be.participation.Participation;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.util.List;

@Entity(name="users")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String birth;
    private Integer studentId;
    private String college;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany()
    List<Participation> participationList;

    @OneToMany()
    private List<Message> messages;

    public User(String nickname, String email, String password, UserRole role, String birth, Integer studentId, String college, Gender gender) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.birth = birth;
        this.studentId = studentId;
        this.college = college;
        this.gender = gender;
    }

    public static User createAdmin(String nickname, String email, String password, String birth, Integer studentId, String college, String gender){
        return new User(nickname, email, password, UserRole.ADMIN, birth, studentId, college, Gender.valueOf(gender));
    }
    public static User createUser(String nickname, String email, String password, String birth, Integer studentId, String college, String gender){
        return new User(nickname, email, password, UserRole.DEFAULT, birth, studentId, college, Gender.valueOf(gender));
    }
}
