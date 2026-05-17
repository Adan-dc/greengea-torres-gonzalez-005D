-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2026 a las 03:53:11
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_clientes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_creado` date DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `rut` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `apellido`, `email`, `fecha_creado`, `nombre`, `rut`) VALUES
(1, 'González', 'gabriel@correo.com', '2026-05-13', 'Gabriel', '19123456-7'),
(2, 'Torres', 'adan@correo.com', '2026-05-14', 'Adan', '22555321-7'),
(3, 'Cabrera', 'Matias@correo.com', '2026-05-14', 'Matias', '22334212-7'),
(4, 'Galvez', 'cristobal@correo.com', '2026-05-14', 'Cristobal', '22334212-7');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion_cliente`
--

CREATE TABLE `direccion_cliente` (
  `id` bigint(20) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `comuna` varchar(255) NOT NULL,
  `numero_casa` int(11) NOT NULL,
  `region` varchar(255) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `direccion_cliente`
--

INSERT INTO `direccion_cliente` (`id`, `calle`, `ciudad`, `comuna`, `numero_casa`, `region`, `cliente_id`) VALUES
(1, 'Avenida Los Pajaritos', 'Santiago', 'Maipú', 1234, 'Metropolitana', 1),
(2, 'Avenida Los Torresini', 'Santiago', 'Maipú', 4321, 'Metropolitana', 2),
(3, 'Avenida Los Pechamistas', 'Santiago', 'Maipú', 4334, 'Metropolitana', 3),
(4, 'Avenida Los Simpson', 'Santiago', 'Maipú', 3244, 'Metropolitana', 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `direccion_cliente`
--
ALTER TABLE `direccion_cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9nxg2le2ko8pytvgv327ihou6` (`cliente_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `direccion_cliente`
--
ALTER TABLE `direccion_cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `direccion_cliente`
--
ALTER TABLE `direccion_cliente`
  ADD CONSTRAINT `FK433jjp4rom328f157ny82ygse` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
