package Database;

import java.sql.SQLException;
import java.util.List;

public interface IGetterData
{
    <T> List<T> GetData() throws SQLException;
}
