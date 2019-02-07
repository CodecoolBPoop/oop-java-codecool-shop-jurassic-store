INSERT INTO suppliers(name,description)
VALUES('Bayer','Bayer AG is a German multinational pharmaceutical and life sciences company and one of the largest pharmaceutical companies in the world.');

INSERT INTO suppliers(name,description)
VALUES('Monsanto','The Monsanto Company was an American agrochemical and agricultural biotechnology corporation.');

INSERT INTO suppliers(name,description)
VALUES('Dow','The Dow Chemical Company, commonly referred to as Dow, is an American multinational chemical corporation headquartered in Midland, Michigan, United States, and the predecessor of the merged company DowDuPont.');

INSERT INTO product_cat(description, department, name)
VALUES('Vegan','Dinosaur','Herbivorous');

INSERT INTO product_cat(description, department, name)
VALUES('Not vegan','Dinosaur','Carnivorous');

INSERT INTO product_cat(description, department, name)
VALUES('Not that vegan','Dinosaur','Omnivorous');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(1,2,'T-rex (Bayer, carnivorous)','A genus of coelurosaurian theropod dinosaur. The species Tyrannosaurus rex (rex meaning \"king\" in Latin), is one of the most well-represented of the large theropods.',999,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(3,3,'Velociraptor (Dow, omnivorous)','A genus of dromaeosaurid theropod dinosaur that lived approximately 75 to 71 million years ago during the latter part of the Cretaceous Period.',479,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(1,2,'Diplodocus (Monsanto, herbivorous)','One of the longest dinosaurs, Diplodocus had a unique body construction, with two rows of bones on the underside of its tail to provide extra support and greater mobility.',249,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(3,1,'Oviraptor (Bayer, omnivorous)','a genus of small Mongolian theropod dinosaurs, first discovered by technician George Olsen in an expedition led by Roy Chapman Andrews, and first described by Henry Fairfield Osborn, in 1924.',900,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(2,2,'Argentinosaurus (Monsanto, carnivorous)','a genus of titanosaur sauropod dinosaur first discovered by Guillermo Heredia in Argentina. The generic name refers to the country in which it was discovered.',849,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(2,1,'Giganotosaurus (Bayer, carnivorous)','Giganotosaurus was one of the largest meat-eating dinosaurs. It roamed modern-day Argentina during the late Cretaceous Period, about 99.6 to 97 million years ago.',139,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(3,3,'Caudipteryx (Dow, omnivorous)','Caudipteryx is a genus of peacock-sized theropod dinosaurs that lived in the Aptian age of the early Cretaceous Period (about 124.6 million years ago). They were feathered and remarkably birdlike in their overall appearance.',200,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(1,3,'Gargoyleosaurus (Dow, herbivorous)','Gargoyleosaurus is one of the earliest ankylosaurs known from reasonably complete fossil remains. Its skull measures 29 centimetres in length, and its total body length is an estimated 3 to 4 metres. It may have weighed as much as 1 tonne.',690,'USD');

INSERT INTO products(supplier_id, category_id, name, description, price, currency)
VALUES(1,2,'Triceratops (Monsanto, herbivorous)','Triceratops was a very common dinosaur which lived at the very end of the Cretaceous period. It had a huge frilled head with horns over each eye that could reach over 4 feet long. Triceratops had a third, smaller horn on its nose. These would be fearsome weapons against a predator.',749,'USD');