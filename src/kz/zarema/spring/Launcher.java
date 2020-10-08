package kz.zarema.spring;

import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            HumanDAO humanDAO = (HumanDAO) context.getBean("HumanDAO"); // Загрузка бина доступа к таблице списка игроков 

            humanDAO.deleteAll(); // Удаление всех записей

            human human = new human("Мануэль", "Германия", 34); // Создание нового объекта таблицы списка игроков 
            humanDAO.insert(human); // Вставить новый объект (запись) в таблицу списка игроков

            humanDAO.insert(new human("Роберт", "Польша", 32)); // Вставить новый объект (запись) в таблицу списка игроков
            humanDAO.insert(new human("Томас", "Германия", 31));
            humanDAO.insert(new human("Серж", "Германия", 25));
            humanDAO.insert(new human("Йозуа", "Германия", 25));
            humanDAO.insert(new human("Альфонсо", "Канада", 19));
            humanDAO.insert(new human("Марк", "Испания", 23));
            humanDAO.insert(new human("Бенжамен", "Франция", 24));

            System.out.println("\nИзначальное состояние БД:");

            List<human> list = humanDAO.selectAll();
            for (human myHuman : list) {
                System.out.println(myHuman.getName() + " " + myHuman.getCountry() + " " + myHuman.getAge());
            }

            System.out.println("\nВывод записи, в которой возраст игрока равен 23");
            human human1 = humanDAO.findByAge(23); // Поиск записи по возрасту
            System.out.println(human1 != null ? human1 : "Нет данных"); // Вывод на экран найденной записи

            humanDAO.deleteByCountry("Кан");
            humanDAO.delete("Марк", "Испания");

            System.out.println("\nВывод записи, содержащей фрагмент имени 'зу'");
            List<human> Human = humanDAO.findByName("зу"); // Поиск записей по фрагменту имени
            System.out.println(Human != null ? Human : "Нет данных");

            System.out.println("\nИзменение значения страны в таблице с 'Германия' на 'Франция'");
            humanDAO.update("Германия", "Франция");
            System.out.println("\nУдаление записей по фрагменту страны 'Кан'");

            System.out.println("\nУдаление записей по имени 'Марк' и стране 'Испания'");

            System.out.println("\nКонечное состояние БД:");

            List<human> list1 = humanDAO.selectAll();
            list1.forEach((myHuman) -> {
                System.out.println(myHuman.getName() + " " + myHuman.getCountry() + " " + myHuman.getAge());
            });

            System.out.println("\nВывод записей с именем 'Роберт' и страной 'Польша':");
            list = humanDAO.select("Роберт", "Польша");
            for (human myHuman : list) {
                System.out.println(myHuman.getName() + " " + myHuman.getCountry());
            }
        } catch (BeansException e) {
            System.out.println("Error!");
        }
    }
}
