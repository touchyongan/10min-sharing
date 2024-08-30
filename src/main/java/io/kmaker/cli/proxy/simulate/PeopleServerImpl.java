package io.kmaker.cli.proxy.simulate;

import io.kmaker.cli.proxy.simulate.entity.People;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class PeopleServerImpl implements PeopleService {

    @Override
//    @SneakyThrows
//    @Transactional
    public void add(People people) {
        addInternal(people);
    }

    @Transactional
    @SneakyThrows
    private void addInternal(People people) {
        final var connection = ConnectionManager.getConnection();
        final var stmt = connection.prepareStatement("INSERT INTO people(first_name, last_name, gender) VALUES(?, ?, ?)");
        stmt.setString(1, people.getFirstName());
        stmt.setString(2, people.getLastName());
        stmt.setString(3, people.getGender());
        stmt.execute();
        throw new RuntimeException("Simulate fail scenario");
    }

    @Override
    @SneakyThrows
    public List<People> getAll() {
        final var sql = "SELECT * FROM people p";
        final var connection = ConnectionManager.getConnection();
        final var stmt = connection.prepareStatement(sql);
        final var rs = stmt.executeQuery();
        final var result = new ArrayList<People>();
        while (rs.next()) {
            final var people = new People();
            people.setId(rs.getInt("id"));
            people.setFirstName(rs.getString("first_name"));
            people.setLastName(rs.getString("last_name"));
            people.setGender(rs.getString("gender"));
            result.add(people);
        }
        return result;
    }
}
