package kz.zarema.spring;

import javax.sql.DataSource;
import java.util.List;

public interface IHumanDAO {

    void setDataSource(DataSource ds); // Установка связи с данными

    void insert(human customer); // Вставка новой записи

    void append(String name, String country, int age);

    void deleteByCountry(String country);

    void delete(String name, String country); // Удаление записи с указанным именем

    void deleteAll(); // Удаление всех записей

    void update(String oldCountry, String newCountry);

    human findByAge(int age); // Получение записи с заданным возрастом

    List<human> findByName(String name); // Нахождение записей с заданным именем

    List<human> select(String name, String country); // Выбор записей с заданным именем

    List<human> selectAll();
}
