package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Type;

import java.util.List;

public interface TypeService extends Service<Type> {
   boolean create(String name);

   long findIdByName(String name);
}
