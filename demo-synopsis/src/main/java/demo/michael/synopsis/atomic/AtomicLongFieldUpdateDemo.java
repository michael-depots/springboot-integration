package demo.michael.synopsis.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @className: AtomicLongFieldUpdateDemo
 * @description: AtomicLongFieldUpdater 和 AtomicReferenceFieldUpdater 使用
 * @author: charles
 * @date: 12/29/21
 **/
public class AtomicLongFieldUpdateDemo {

    public static void main(String[] args) {
        AtomicLongFieldUpdater<Student> longFieldUpdater = AtomicLongFieldUpdater.newUpdater(Student.class, "id");
        Student student = new Student(1L, "charles");
        longFieldUpdater.compareAndSet(student, 1L, 100L);
        System.out.println("id = " + student.getId());

        AtomicReferenceFieldUpdater<Student, String> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        Student student2 = new Student(1L, "charles");
        referenceFieldUpdater.compareAndSet(student2, "charles", "charles.shao");
        System.out.println("name = " + student2.getName());
    }
}

class Student {
    volatile long id;
    volatile String name;

    public Student(long id, String name) {
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
