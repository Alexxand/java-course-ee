package listeners;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import controllers.AddController;
import controllers.DeleteController;
import controllers.DisplayController;
import controllers.ImageController;
import db.DAO;
import db.PgDAOImpl;
import db.PgDataSourceProvider;
import filters.CharsetFilter;
import filters.LocaleFilter;

import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;;import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class GuiceConfig extends GuiceServletContextListener {

    private static class DbModule extends AbstractModule{

        @Override
        protected void configure() {
            final Properties dbConfig = new Properties();
            try(InputStream stream = getClass().getClassLoader().getResourceAsStream("cfg/postgres.properties")) {
                dbConfig.load(stream);
            } catch (IOException e){
                e.printStackTrace();
            }
            Names.bindProperties(binder(),dbConfig);
            bind(DataSource.class).toProvider(PgDataSourceProvider.class).in(Singleton.class);
            bind(DAO.class).to(PgDAOImpl.class).in(Singleton.class);
        }
    }

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets() {
            serve("/add","/").with(AddController.class);
            serve("/delete").with(DeleteController.class);
            serve("/display").with(DisplayController.class);
            serve("/image").with(ImageController.class);
            filter("/*").through(CharsetFilter.class);
            filter("/*").through(LocaleFilter.class);
        }
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new DbModule(),new ServletConfigModule());
    }
}