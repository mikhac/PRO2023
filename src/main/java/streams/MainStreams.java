package streams;

import streams.LocalVariables;
import streams.model.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainStreams {

    public static void main(String[] args) {
        LocalVariables example = new LocalVariables();
        example.method();

        // Old Java
        List<Student> students = new ArrayList<>();
        students.add(new Student("Arnold", 1L, 2L));
        students.add(new Student("John", 1L, 3L));
        students.add(new Student("Barrack", 2L, 1L));
        List<String> names = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().startsWith("A")) {
                names.add(student.getName());
            }
        }

        // Java 8, and above
        List<String> namesNewJava = students.stream()
//                .map(student -> student.getName())
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        // stream
        Stream.of("This", "is", "Java8", "Stream").forEach(System.out::println);

        String[] stringArray = new String[]{"Streams", "can", "be", "created", "from", "arrays"};
        Arrays.stream(stringArray).forEach(System.out::println);

//        IntStream.rangeClosed(1, 10).forEach(System.out::println);
        students.stream()
                .map(Student::getYear)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        Map<Boolean, List<Student>> yearsParity = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getYear() % 2 == 0));
        yearsParity.keySet().forEach(key -> System.out.println(yearsParity.get(key)));
    }
}
