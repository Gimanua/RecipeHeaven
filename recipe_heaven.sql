-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Värd: 127.0.0.1
-- Tid vid skapande: 04 mars 2020 kl 16:10
-- Serverversion: 10.4.8-MariaDB
-- PHP-version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `recipe_heaven`
--
CREATE DATABASE IF NOT EXISTS `recipe_heaven` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `recipe_heaven`;

DELIMITER $$
--
-- Procedurer
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `complete_recipe` (IN `recipe_id` INT)  NO SQL
BEGIN
SELECT * FROM recipe_info WHERE recipe_info.recipe_id = recipe_id;
SELECT * FROM category_info WHERE category_info.recipe_id = recipe_id;
SELECT * FROM recipe_ingredient_info WHERE recipe_ingredient_info.recipe_id = recipe_id;
SELECT * FROM instructions WHERE instructions.recipe_id = recipe_id;
SELECT * FROM comment_info WHERE comment_info.recipe_id = recipe_id;
SELECT * FROM reply_info WHERE reply_info.comment_id IN (SELECT comment_info.comment_id FROM comment_info WHERE comment_info.recipe_id = recipe_id);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellstruktur `categories`
--

CREATE TABLE `categories` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Ersättningsstruktur för vy `category_info`
-- (See below for the actual view)
--
CREATE TABLE `category_info` (
`recipe_id` int(10) unsigned
,`category_id` int(10) unsigned
,`category_name` varchar(128)
);

-- --------------------------------------------------------

--
-- Tabellstruktur `comments`
--

CREATE TABLE `comments` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `recipe_id` int(10) UNSIGNED NOT NULL,
  `comment` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Ersättningsstruktur för vy `comment_info`
-- (See below for the actual view)
--
CREATE TABLE `comment_info` (
`recipe_id` int(10) unsigned
,`comment_id` int(10) unsigned
,`comment` varchar(512)
,`poster_username` varchar(32)
);

-- --------------------------------------------------------

--
-- Tabellstruktur `ingredients`
--

CREATE TABLE `ingredients` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellstruktur `instructions`
--

CREATE TABLE `instructions` (
  `recipe_id` int(10) UNSIGNED NOT NULL,
  `order_index` int(10) UNSIGNED NOT NULL,
  `description` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellstruktur `likes`
--

CREATE TABLE `likes` (
  `user_id` int(11) UNSIGNED NOT NULL,
  `recipe_id` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellstruktur `recipes`
--

CREATE TABLE `recipes` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `image` varchar(1024) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` varchar(4096) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellstruktur `recipe_categories`
--

CREATE TABLE `recipe_categories` (
  `recipe_id` int(10) UNSIGNED NOT NULL,
  `category_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Ersättningsstruktur för vy `recipe_info`
-- (See below for the actual view)
--
CREATE TABLE `recipe_info` (
`recipe_id` int(10) unsigned
,`likes` bigint(21)
,`name` varchar(256)
,`poster_username` varchar(32)
,`image` varchar(1024)
,`description` varchar(4096)
);

-- --------------------------------------------------------

--
-- Tabellstruktur `recipe_ingredients`
--

CREATE TABLE `recipe_ingredients` (
  `recipe_id` int(10) UNSIGNED NOT NULL,
  `ingredient_id` int(10) UNSIGNED NOT NULL,
  `amount` decimal(4,3) UNSIGNED NOT NULL,
  `unit_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Ersättningsstruktur för vy `recipe_ingredient_info`
-- (See below for the actual view)
--
CREATE TABLE `recipe_ingredient_info` (
`recipe_id` int(10) unsigned
,`ingredient_id` int(10) unsigned
,`amount` decimal(4,3) unsigned
,`unit_abbreviation` varchar(16)
,`unit_name` varchar(32)
,`name` varchar(128)
);

-- --------------------------------------------------------

--
-- Tabellstruktur `replies`
--

CREATE TABLE `replies` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `comment_id` int(10) UNSIGNED NOT NULL,
  `reply` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Ersättningsstruktur för vy `reply_info`
-- (See below for the actual view)
--
CREATE TABLE `reply_info` (
`comment_id` int(10) unsigned
,`reply_id` int(10) unsigned
,`reply` varchar(512)
,`poster_username` varchar(32)
);

-- --------------------------------------------------------

--
-- Tabellstruktur `reported_recipes`
--

CREATE TABLE `reported_recipes` (
  `user_id` int(10) UNSIGNED NOT NULL,
  `recipe_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellstruktur `units`
--

CREATE TABLE `units` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(32) NOT NULL,
  `abbreviation` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabellstruktur `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(32) NOT NULL,
  `oauth_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur för vy `category_info`
--
DROP TABLE IF EXISTS `category_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `category_info`  AS  select `recipe_categories`.`recipe_id` AS `recipe_id`,`recipe_categories`.`category_id` AS `category_id`,`categories`.`name` AS `category_name` from (`recipe_categories` join `categories` on(`recipe_categories`.`category_id` = `categories`.`id`)) ;

-- --------------------------------------------------------

--
-- Struktur för vy `comment_info`
--
DROP TABLE IF EXISTS `comment_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `comment_info`  AS  select `comments`.`recipe_id` AS `recipe_id`,`comments`.`id` AS `comment_id`,`comments`.`comment` AS `comment`,`users`.`username` AS `poster_username` from (`comments` join `users` on(`comments`.`user_id` = `users`.`id`)) ;

-- --------------------------------------------------------

--
-- Struktur för vy `recipe_info`
--
DROP TABLE IF EXISTS `recipe_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recipe_info`  AS  select `recipes`.`id` AS `recipe_id`,(select count(`likes`.`recipe_id`) from `likes` where `likes`.`recipe_id` = `recipes`.`id`) AS `likes`,`recipes`.`name` AS `name`,`users`.`username` AS `poster_username`,`recipes`.`image` AS `image`,`recipes`.`description` AS `description` from (`recipes` join `users` on(`users`.`id` = `recipes`.`user_id`)) ;

-- --------------------------------------------------------

--
-- Struktur för vy `recipe_ingredient_info`
--
DROP TABLE IF EXISTS `recipe_ingredient_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `recipe_ingredient_info`  AS  select `recipe_ingredients`.`recipe_id` AS `recipe_id`,`recipe_ingredients`.`ingredient_id` AS `ingredient_id`,`recipe_ingredients`.`amount` AS `amount`,`units`.`abbreviation` AS `unit_abbreviation`,`units`.`name` AS `unit_name`,`ingredients`.`name` AS `name` from ((`recipe_ingredients` join `ingredients` on(`recipe_ingredients`.`ingredient_id` = `ingredients`.`id`)) left join `units` on(`recipe_ingredients`.`unit_id` = `units`.`id`)) ;

-- --------------------------------------------------------

--
-- Struktur för vy `reply_info`
--
DROP TABLE IF EXISTS `reply_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `reply_info`  AS  select `replies`.`comment_id` AS `comment_id`,`replies`.`id` AS `reply_id`,`replies`.`reply` AS `reply`,`users`.`username` AS `poster_username` from (`replies` join `users` on(`replies`.`user_id` = `users`.`id`)) ;

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index för tabell `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `comments_ibfk_2` (`recipe_id`);

--
-- Index för tabell `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Index för tabell `instructions`
--
ALTER TABLE `instructions`
  ADD PRIMARY KEY (`recipe_id`,`order_index`);

--
-- Index för tabell `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`user_id`,`recipe_id`),
  ADD KEY `likes_ibfk_2` (`recipe_id`);

--
-- Index för tabell `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Index för tabell `recipe_categories`
--
ALTER TABLE `recipe_categories`
  ADD PRIMARY KEY (`recipe_id`,`category_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Index för tabell `recipe_ingredients`
--
ALTER TABLE `recipe_ingredients`
  ADD PRIMARY KEY (`recipe_id`,`ingredient_id`),
  ADD KEY `unit_id` (`unit_id`),
  ADD KEY `ingredient_id` (`ingredient_id`);

--
-- Index för tabell `replies`
--
ALTER TABLE `replies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `comment_id` (`comment_id`);

--
-- Index för tabell `reported_recipes`
--
ALTER TABLE `reported_recipes`
  ADD PRIMARY KEY (`user_id`,`recipe_id`),
  ADD KEY `reported_recipes_ibfk_2` (`recipe_id`);

--
-- Index för tabell `units`
--
ALTER TABLE `units`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `abbreviation` (`abbreviation`);

--
-- Index för tabell `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `oauth_id` (`oauth_id`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT för tabell `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT för tabell `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT för tabell `recipes`
--
ALTER TABLE `recipes`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT för tabell `replies`
--
ALTER TABLE `replies`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT för tabell `units`
--
ALTER TABLE `units`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT för tabell `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE;

--
-- Restriktioner för tabell `instructions`
--
ALTER TABLE `instructions`
  ADD CONSTRAINT `instructions_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE;

--
-- Restriktioner för tabell `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE;

--
-- Restriktioner för tabell `recipes`
--
ALTER TABLE `recipes`
  ADD CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Restriktioner för tabell `recipe_categories`
--
ALTER TABLE `recipe_categories`
  ADD CONSTRAINT `recipe_categories_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `recipe_categories_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Restriktioner för tabell `recipe_ingredients`
--
ALTER TABLE `recipe_ingredients`
  ADD CONSTRAINT `recipe_ingredients_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `recipe_ingredients_ibfk_2` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  ADD CONSTRAINT `recipe_ingredients_ibfk_3` FOREIGN KEY (`unit_id`) REFERENCES `units` (`id`);

--
-- Restriktioner för tabell `replies`
--
ALTER TABLE `replies`
  ADD CONSTRAINT `replies_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `replies_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`) ON DELETE CASCADE;

--
-- Restriktioner för tabell `reported_recipes`
--
ALTER TABLE `reported_recipes`
  ADD CONSTRAINT `reported_recipes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `reported_recipes_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
