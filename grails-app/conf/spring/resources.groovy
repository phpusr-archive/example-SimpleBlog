// Place your Spring DSL code here
beans = {
    /**
     * Язык по умолчанию
     * Параметр для переключения по ссылке: lang=en
     */
    localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver) {
        defaultLocale = new Locale("en","EN")
        java.util.Locale.setDefault(defaultLocale)
    }
}
