package io.kmaker.cli.proxy.simulate;

import io.kmaker.cli.proxy.simulate.entity.People;

import java.util.List;

public interface PeopleService {

    void add(People people);

    List<People> getAll();
}
