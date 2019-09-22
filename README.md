# tommultimodule2
There is a simple news site web application with such technologies as java, servlets, jsp, jstl, jdbc.
It is the multi module project consists of submodules: dao, models, service, web.

*Web servlets use login and password registration.*

*The access to user's pages is controlled by filter.*

*The topsecret pages is controlled by second level filter.*

*The type of application database is mysql.*

*It is used cobertura for viewing testing coverage of the project.*

*Testing libraries are junit and mokito.*

*Models: user, news.*

Map and navigation on the site.
------------------------------

There is a main (index) page of application.
The page has links to login(Вход), registration(Регистрация) and news pages(Новости).
You can use existing user login-"qwe" and password-"123", role:"user" or 
create your own in the registration form of the registration page or
 test web application by entrance of admin login-"admin" and password-"admin".

On the news page(Новости) you can see titles, creation dates and news data
that were added in a database some time before.

You appear on the welcome page after positive authorization.
There are links to the main page(Главная), user page(Страница пользователя) and logout.
In case you have administrator role you can visit administrator pages(Страница администратора, Страница администратора2).
User page has a link to addition of user news on a page of web application.
 

