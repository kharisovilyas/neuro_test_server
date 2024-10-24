package ru.ilcorp.neuro_test.model.entity.classroom;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.ilcorp.neuro_test.model.entity.user.StudentUserEntity;
import ru.ilcorp.neuro_test.model.entity.user.TeacherUserEntity;

import java.util.List;

@Entity
@Table(name = "classroom")
public class ClassroomEntity {
    @Id
    @Column(name = "id_classroom")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroomId;

    @Embedded
    private ClassroomCode classroomCode;
    @OneToMany(mappedBy = "classroomEntity", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<StudentUserEntity> studentUserEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_teacher")
    private TeacherUserEntity teacherUserEntity;

    public ClassroomEntity() {
    }
}
