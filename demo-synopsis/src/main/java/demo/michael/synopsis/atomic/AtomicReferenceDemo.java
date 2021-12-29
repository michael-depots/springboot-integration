package demo.michael.synopsis.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @className: AtomicReferenceDemo
 * @description: AtomicReference 使用
 * @author: charles
 * @date: 12/29/21
 **/
public class AtomicReferenceDemo {

    public static void main(String[] args) {

        AtomicReference<Teacher> teacherAtomicReference = new AtomicReference<>();
        Teacher teacher = new Teacher(1L, "charles");
        Teacher teacher2 = new Teacher(2L, "charles2");

        teacherAtomicReference.set(teacher);
        teacherAtomicReference.compareAndSet(teacher, teacher2);
        Teacher teacher3 = teacherAtomicReference.get();
        System.out.println(teacher3.getName());
    }
}

class Teacher {
    private long id;
    private String name;

    public Teacher(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
