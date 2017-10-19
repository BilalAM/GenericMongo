# GenericMongo
A Generic DAO for Mongo using MongoDB API Java

No external library used for mapping EXCEPT google.gson, all mapping is done by owners own logics and implimentations

**Uses google gson library and mongo Json library for serializing and deserializing purposes**

**dao** --> Abstract And Concrete implimentations of data access classes ( Standard generic Dao pattern)

**models** --> POJO classes

**scaffolder** --> Generates the concrete model dao classes instead of writing redundant classes ( PLEASE SEE Program.java TO UNDERSTAND HOW IT ALL WORKS  

**mapper**  --> An attempt to create a Object Document Modelling (ORMs for non-relational databases). Project still underway , maybe subjected to extensive edits and revisions




## mapper ##

mapper is an attempt to create an easy to use ORM for MongoDb and Java

**Description**

Suppose you have a Mongo Database named ***ProductDB*** with collections ***CustomerCollection*** and ***ProductCollection***

Now just like we create mappings in **hibernate** , you define a xml mapping file as follows :

    <db name="ProductDB">
		<collection name="CustomerCollection" class="Customer.java">
			<attribute name="CustomerName" key="customer_id" type="int" />
			<attribute name="CustomerAddress" key="customer_add" type="String" />
		</collection>
		<collection name="ProductCollection" class="Product.java">
			<attribute name="ProductName" key="prod_name" type="String" />
			<attribute name="ProductPrice" key="prod_price" type="double" />
		</collection>
</db>
	
	
### Tags description

 - **Database tag (db)**
	 - Has only one attribute **name** , the name of a existing mongo database

 - **Collection tag (collection)**
	 -  has two attributes **name** and **class** , **name** is for the existing collection in your database and **class** is for the name of the class that the collection will be mapped into.
 
 
 -     **Attribute tag (attribute) ** 
	 - has three attributes **name** , **key** and
   **type** . **name** is for the NEW variable you want to create , **key** is for the EXISTING key attribute in your collection and **type** is for the data type of variable.

### Project is still underway :) ......
 





