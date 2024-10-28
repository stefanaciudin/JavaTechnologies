# Laborator 3

JavaServer Faces
Create a Web application using JavaServer Faces technology, dedicated to the Vehicle Routing Problem.
Consider a Cartesian map, each location of the map having (x,y) coordinates. There is a warehouse located at (0,0) and a number of clients located at random coordinates. In the warehouse, there are products. The clients request one (ore more) products from the map, having specific constraints, related to the days and time intervals when they are available to receive their products.
The problem is to create a schedule for delivering the products to the clients.

The main specifications of the application are:

## Compulsory 

Create the following:

A relational database in order to store and retrieve data. (PostgreSQL is recommended). You should have at least the table for products, having some predefined data.
A JSF project, using Maven.
A web page for viewing the products in the database.
It is recommended to use an Ajax-based JSF implementation, such as PrimeFaces.

Pentru partea compulsory, s-a creat o baza de date PostgreSQL dupa structura definita in fisierul Database creation query. Tabelele au fost populte cu un script cu cateva comenzi de insert.
S-au creat clasele Product si ProductBean, care au fost ulterior utilizate pentru vizualizarea listei de produse definite in baza de date.


## Homework 

Create pages for viewing products and clients using datatables.
Create a page for adding a new client or editing an existing one, using a dialog.
Use appropriate components for specifying the properties of a client, such as: DatePicker, SelectOneMenu, PickList, etc.
Define the web-flow using navigation-rules.
Internationalize the user interface and offer support for at least two locales.

Pentru partea homework, am adaugat clasele Client and ClientBean pentru a crea pagina pentru vizualizarea clientilor din baza de date. Prin intermediul acestor clase este posibila si functionalitatea de adaugare si editare a clientilor existenti, folosing un dialog. S-a adaugat si clasa LocaleBean - aceasta este utilizata in procesul de internationalizare, pentru a oferi utilizatorilor posibilitatea de a avea aplicatia disponibila in mai multe limbi - engleza si franceza. Aceste proprietati sunt definite in fisierele .properties specifice.