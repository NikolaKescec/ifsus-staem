package hr.fer.infsus.staem.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class StaemEntityIdGenerator implements IdentifierGenerator {

    private static final String MAXIMUM_ID_QUERY = "SELECT MAX(id) FROM %s;";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        final Connection connection = session.connection();

        try (final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(
                String.format(MAXIMUM_ID_QUERY, object.getClass().getSimpleName().toLowerCase(Locale.ENGLISH)));
            if (resultSet.next()) {
                return resultSet.getLong(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
