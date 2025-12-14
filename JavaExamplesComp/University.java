class Student {
    private String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}

class University {  // Агрегация: студенты передаются извне
    private java.util.List<Student> students = new java.util.ArrayList<>();
    
    public void addStudent(Student student) {  // Передача извне
        students.add(student);
    }
    
    public void printStudents() {
        for (Student s : students) {
            System.out.println("Студент: " + s.getName());
        }
    }
}
