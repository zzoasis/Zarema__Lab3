package kz.zarema.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.List;

public class HumanDAO implements IHumanDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(human customer) { // Реализация вставки новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO HUMAN (NAME, COUNTRY, AGE) VALUES(?,?,?)",
                new Object[]{customer.getName(), customer.getCountry(), customer.getAge()});
    }

    @Override
    public void append(String name, String country, int age) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO HUMAN (NAME, COUNTRY, AGE) VALUES(?,?,?)", new Object[]{name, country, age});
    }

    @Override
    public void deleteByCountry(String country) {  // Реализация удаления записей по стране
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM HUMAN WHERE COUNTRY LIKE ?", new Object[]{'%' + country + '%'});
    }

    @Override

    public void delete(final String name, final String country) {  // Реализация удаления записей с указанным именем и страной
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from HUMAN where NAME = ? AND COUNTRY = ?", new Object[]{name, country});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from HUMAN");
    }

    @Override
    public void update(String oldCountry, String newCountry) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE HUMAN SET COUNTRY = ? WHERE COUNTRY = ?", new Object[]{newCountry, oldCountry});
    }

    @Override
    public human findByAge(int age) { // Реализация поиска записи с заданным возрастом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        List<human> human = select.query("SELECT * FROM HUMAN WHERE AGE = ?",
                new Object[]{age}, new HumanRowMapper());
        return human.size() > 0 ? human.get(0) : null;
    }

    @Override
    public List<human> findByName(String country) {  // Реализация поиска записей по имени
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM HUMAN WHERE NAME LIKE ?";
        List<human> Country = select.query(sql, new Object[]{'%' + country + '%'}, new HumanRowMapper());
        return Country;
    }

    @Override
    public List<human> select(String name, String country) {  // Реализация получения записей с заданным именем и страной
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from HUMAN where NAME = ? AND COUNTRY= ?",
                new Object[]{name, country}, new HumanRowMapper());
    }

    @Override
    public List<human> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from HUMAN", new HumanRowMapper());
    }
}