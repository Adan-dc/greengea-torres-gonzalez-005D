-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2026 a las 03:53:04
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
-- Base de datos: `db_catalogo_producto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'Encuadernación'),
(2, 'Papelería'),
(3, 'Manualidades');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `dimensiones` float NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `peso_kilos` float NOT NULL,
  `precio_base` int(11) NOT NULL,
  `categoria_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `codigo`, `dimensiones`, `nombre`, `peso_kilos`, `precio_base`, `categoria_id`) VALUES
(1, 'PROD-001', 50, ' ÁLBUM HOJAS NEGRAS ', 8, 16490, 1),
(2, 'PROD-002', 46, ' ÁLBUM HOJAS ROJAS', 7.6, 26800, 1),
(3, 'PROD-003', 50, ' ÁLBUM HOJAS PLÁSTICAS', 5.5, 21600, 1),
(4, 'PROD-004', 47, ' PLANNER ARIES', 9, 16490, 1),
(5, 'PROD-005', 47, ' PLANNER TAURO', 9, 16490, 1),
(6, 'PROD-006', 47, ' PLANNER GÉMINIS', 9, 16490, 1),
(7, 'PROD-007', 47, ' PLANNER CÁNCER', 9, 16490, 1),
(8, 'PROD-008', 47, ' PLANNER LEO', 9, 16490, 1),
(9, 'PROD-009', 47, ' PLANNER VIRGO', 9, 16490, 1),
(10, 'PROD-010', 180, ' SOBRE 120 GRAMOS', 2.4, 14490, 2),
(11, 'PROD-11', 150, ' Vidrio con vinilo negro', 0.9, 6490, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKodqr7965ok9rwquj1utiamt0m` (`categoria_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FKodqr7965ok9rwquj1utiamt0m` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
