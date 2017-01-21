package listeners;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import controllers.AddController;
import controllers.DeleteController;
import controllers.DisplayController;
import filters.LocaleFilter;

import javax.servlet.annotation.WebListener;

import static jdk.nashorn.internal.objects.NativeFunction.bind;

@WebListener
public class GuiceConfig extends GuiceServletContextListener {

    private static class ServletConfigModule extends ServletModule {
        @Override
        protected void configureServlets() {
            serve("/add","/").with(AddController.class);
            serve("/delete").with(DeleteController.class);
            serve("/display").with(DisplayController.class);
            filter("/*").through(LocaleFilter.class);
        }
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletConfigModule());
    }
}