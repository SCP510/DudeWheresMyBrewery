BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, beer, brewery, beer_review, brewery_review, favorite_beer, favorite_brewery;

CREATE TABLE users 
(
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,

	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE brewery 
(
    brewery_id SERIAL,
    brewery_name VARCHAR(50) NOT NULL UNIQUE,
    street_address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    date_est DATE NULL,
    phone_number VARCHAR(20) NOT NULL,
    about_us VARCHAR (1000) NOT NULL,
    logo_image VARCHAR(1000),
    website VARCHAR (50),
    founder_id INT NOT NULL,

    CONSTRAINT PK_brewery PRIMARY KEY (brewery_id),
    CONSTRAINT FK_brewery_users FOREIGN KEY (founder_id) REFERENCES users(user_id)
);

CREATE TABLE beer 
(
    beer_id SERIAL,
    beer_name VARCHAR(100) NOT NULL UNIQUE,
    brewery_id INT NOT NULL,
    beer_type VARCHAR(50) NOT NULL,
    abv DECIMAL(3, 1) NOT NULL,
    label_image VARCHAR(1000),
    description VARCHAR(1000),

    CONSTRAINT PK_beer PRIMARY KEY (beer_id),
    CONSTRAINT FK_beer_brewery FOREIGN KEY (brewery_id) REFERENCES brewery(brewery_id)
);

CREATE TABLE beer_review 
(
    beer_review_id SERIAL,
    beer_id INT NOT NULL,
    reviewer_id INT NOT NULL,
    beer_rating DECIMAL(2,1) NOT NULL,
    beer_review VARCHAR(500) NOT NULL UNIQUE,
    date_posted timestamp with time zone NOT NULL,

    CONSTRAINT PK_beer_review PRIMARY KEY (beer_review_id),
    CONSTRAINT FK_beer_review_beer FOREIGN KEY (beer_id) REFERENCES beer(beer_id),
    CONSTRAINT FK_beer_review_users FOREIGN KEY (reviewer_id) REFERENCES users(user_id)
);

CREATE TABLE brewery_review 
(
    brewery_review_id SERIAL,
    brewery_id INT NOT NULL,
    reviewer_id INT NOT NULL,
    brewery_rating DECIMAL(2,1) NOT NULL,
    brewery_review VARCHAR(500) NOT NULL UNIQUE,
    date_posted timestamp with time zone NOT NULL,

    CONSTRAINT PK_brewery_review_id PRIMARY KEY (brewery_review_id),
    CONSTRAINT FK_brewery_review_brewery FOREIGN KEY (brewery_id) REFERENCES brewery(brewery_id),
    CONSTRAINT FK_brewery_review_users FOREIGN KEY (reviewer_id) REFERENCES users(user_id)
);

CREATE TABLE favorite_beer 
(
    fav_beer_id SERIAL,
    user_id INT NOT NULL,
    beer_id INT NOT NULL,

    CONSTRAINT PK_favorite_beer PRIMARY KEY (fav_beer_id),
    CONSTRAINT FK_favorite_beer_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_favorite_beer_beer FOREIGN KEY (beer_id) REFERENCES beer(beer_id)
);

CREATE TABLE favorite_brewery 
(
    fav_brewery_id SERIAL,
    user_id INT NOT NULL,
    brewery_id INT NOT NULL,

    CONSTRAINT PK_favorite_brewery PRIMARY KEY (fav_brewery_id),
    CONSTRAINT FK_favorite_brewery_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_favorite_brewery_brewery FOREIGN KEY (user_id) REFERENCES brewery(brewery_id)
);

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

-- starting users
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewertest', '$2a$10$rbU.wF/L6JIVX6C.opN78eDrcDh525uOzn2v7AP70Q/nnz2Qcby/e', 'ROLE_BREWER'); --pass
INSERT INTO users  (username,password_hash,role) VALUES('lovertest','$2a$10$kQtB2r.rMiB.G6XBJxflGepgERn9rjFQUfZ7KW0hC.e7FbR.rw9iq','ROLE_LOVER'); -- pass
INSERT INTO users  (username,password_hash,role) VALUES('brewtoo','$2a$10$tZE.X5Kn/Iqmz91X1AD/qeIAkZxDVmpdfTOKidMNPpwmXRVr.1pDi','ROLE_BREWER'); -- pass

--starting breweries
INSERT INTO brewery (brewery_name, street_address, city, state, zip_code, date_est, phone_number, about_us, logo_image, website, founder_id)
VALUES ('Unity Brewing', '3862 US-30', 'Latrobe', 'PA', 15650, '3-7-2020', '(724) 520-4234',
		'Unity Brewing was something I’ve always wanted to share with the people in my community. I’ve been brewing for 20 years and it only took about about half that time for my hobby to become more of an obsession. Now, when we brew, we’re changing only the subtlest elements of our process. Our Co-Brewer Alan and I have known each other for about the same length of time. He didn’t come into the brewing world until a bit later, but his passion and attention to detail make all the difference. Our beers really are a collective effort and you can taste that. While we offer the traditional styles year round, our menu is constantly changing with new and unique beers.',
		 'unitybrewing.jpg', 'https://unitybrewingpa.com/', 3);
INSERT INTO brewery (brewery_name, street_address, city, state, zip_code, date_est, phone_number, about_us, logo_image, website, founder_id)
VALUES ('Fermata Brewing', '918 Merchant Street', 'Ambridge', 'PA', 15003, '7-25-2021', '(724) 318-8189',
	   'Since the partners are all music enthusiasts, we thought it might be a nice idea to subtly incorporate something music-related when choosing a name or theme for the brewery. Fermata seemed a great idea because it can be connoted several ways. First, the word Fermata is derived from the Italian word fermare, meaning “to stay.” In music, it is used as a symbol to denote a hold (on either a note or a rest) of unspecified length. What better way to encapsulate the idea of a brewpub; we welcome everyone to pause (or stay) for a while and enjoy craft beers and comraderie. It seems to capture the best of both worlds, making for a comfortable and neighborly experience.',
	    'fermata.jpg', 'https://www.fermatabrewing.beer/about-us', 5);
INSERT INTO brewery (brewery_name, street_address, city, state, zip_code, date_est, phone_number, about_us, logo_image, website, founder_id)
VALUES ('New Belgium Brewing', '21 Craven Street', 'Asheville', 'NC', 28806, '3-14-1991', '(828) 333-6900',
	   'New Belgium has been a human-powered company from the beginning. Before our founders ever sold a bottle of beer, they hiked into Rocky Mountain National Park with a jug of home brew to outline the values that would shape our brewery. As a certified B Corp, New Belgium continues its commitment to being a brewery for good. We put people and community first, consider the environmental and social impact of our work, and strive to set the highest standards of taste and quality when it comes to our craft—making great beer.',
	   'newbelgium.jpg','https://www.newbelgium.com/', 5);


--UnityBers
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Vanilla Bean Blonde', 1, 'Blonde Ale', 5.7, 'vanillabeanblonde.jpg', 'The undisputed Pub favorite! Unity Blonde Ale meets real Haitian Vanilla to make this ‘nilla cookie of a beer our best seller!  ');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Unity Lager', 1, 'American Lager', 5.0, 'unitylager.png', 'Easy drinking Lager for beer lovers that love beer that tastes like beer!');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Ridge Runner', 1, 'Hazy IPA', 7.1, 'ridgerunner.png', 'Brewed with Oats, Wheat, and dry hopped with Citra, Loral, and Columbus hops.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Oatmeal Stout', 1, 'Oatmeal Stout', 5.7, 'oatmealstout.png', 'Roasted barley, Oats, Milk Sugar, and Centennial hops. (contains lactose)');

-- Fermata Beers
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Out of Tune: Blueberry Plum', 2, 'Fruited Sour', 7.1, 'outtatuneferm.jpeg', 'Our clean souring process blends perfectly with the sweet and tart flavors of blueberry and plum. These two fruits combine for a symphony of flavors that is satisfying and quenching.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Whole Lot Warmer', 2, 'Winter Warmer', 12.1, 'wholelotwarmer.jpeg', 'The full body, rich toffee, caramel, and traditional spices of our Wee Bit Warmer get even warmer! Aged in rye whiskey barrels from our friends at Big Spring Spirits, Whole Lot Warmer takes on the spicy oak and subtle vanilla from the barrel with a cozy embrace.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Dr. Peters'' Porter', 2, 'English Porter', 5.0, 'peterporter.jpeg', 'Just like all the lads in Blighty, Dr. Peters fancies a proper pint. And when the winds blow across the Mersey and the weather takes a chill, there is no better proper pint than an English Porter. Slightly softer and a tad sweeter than our American Porter, this delightful quaffer has a mild range of restrained roasted flavors with a chocolate-caramel-malty profile that adds a measure of complexity, while remaining deliciously simple.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Autumn Leaves', 2, 'Brown Ale', 5.4, 'autumnleavesferm.jpeg', 'Our Brown Ale features a grist made up of Maris Otter, caramel, and chocolate malts crafted specifically to create a robust, full-bodied beer that has us begging for autumn weather. As the light winds blow, let us pretend we can change the season.');

-- New Belgium Beers
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Voodoo Ranger IPA', 3, 'IPA', 7.0, 'vripa.png', 'Packed with bright tropical aromas and brilliant citrusy flavors, this unfiltered IPA wraps up with a pleasantly smooth finish.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Juice Force', 3, 'Hazy IPA', 9.5, 'jforce.jpeg', 'Juice Force is a fruit forward, highly drinkable, 9.5% ABV blast. Buckle up, with this hazy IPA you''ll be buzzing the tower in no time.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Fat Tire', 3, 'Ale', 5.2, 'fattire.jpeg', 'Made with premium ingredients, Fat Tire is a bright and balanced beer that''s certified carbon neutral. Since 1991, we''ve been working to reduce our environmental impact, while investing a portion of every Fat Tire you buy in community-based climate action. From certified B-Corp New Belgium Brewing, this ale is easy-drinking and easy on the planet.');
INSERT INTO beer (beer_name, brewery_id, beer_type, abv, label_image, description)
VALUES ('Holiday Ale', 3, 'Seasonal Ale', 7.0, 'holidayale.jpeg', 'Holiday Ale captures the best parts of winter: warmth and spice. Brewed by certified B-Corp New Belgium Brewing, this ale—with rich flavors of cranberries, orange and cinnamon—is a real holiday treat. Tis'' the season for complex, rich beers! So, whether you''ve been naughty or nice, you''ll want to put this beer on your beverage wish list. 7.5% ABV.');


--STARTING REVIEWS
INSERT INTO beer_review (beer_id, reviewer_id, beer_rating, beer_review, date_posted)
VALUES (1,4,4.5,'Its aroma carries malty flavors of corn and biscuit. It has a faintly sweet smell and not much hoppiness on the nose. The first taste reveals a bright and mildly sweet blonde that’s heavy on the malt and faint on the hops.',NOW());

INSERT INTO beer_review (beer_id, reviewer_id, beer_rating, beer_review, date_posted)
VALUES (1,4,3.2,'I feel like the aroma reveals this beer is brewed with millet, the taste hints at its corn, and perhaps the gluten free oats can take credit for the body that I appreciate. ',NOW());

INSERT INTO beer_review (beer_id, reviewer_id, beer_rating, beer_review, date_posted)
VALUES (1,4,5.0,'Great blonde ale! Vanilla shines through on the initial taste. Creamy with a hint of sweetness. Now this is just filler text so that Xhelal can style the review card without some weird garbage happening.',NOW());

--starting saved beer list
INSERT INTO favorite_beer (user_id, beer_id) VALUES (2,4); -- admin
INSERT INTO favorite_beer (user_id, beer_id) VALUES (2,5);
INSERT INTO favorite_beer (user_id, beer_id) VALUES (4,6); -- lovertest
INSERT INTO favorite_beer (user_id, beer_id) VALUES (4,2);
INSERT INTO favorite_beer (user_id, beer_id) VALUES (4,3);
INSERT INTO favorite_beer (user_id, beer_id) VALUES (4,4);
INSERT INTO favorite_beer (user_id, beer_id) VALUES (3,1); --brewertest
INSERT INTO favorite_beer (user_id, beer_id) VALUES (5,8);
INSERT INTO favorite_beer (user_id, beer_id) VALUES (5,9);
INSERT INTO favorite_beer (user_id, beer_id) VALUES (5,1);

COMMIT TRANSACTION;
