-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2026 a las 03:53:21
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
-- Base de datos: `db_inventario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE `movimientos` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `motivo` varchar(255) NOT NULL,
  `tipo` bit(1) NOT NULL,
  `stock_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`id`, `cantidad`, `fecha`, `motivo`, `tipo`, `stock_id`) VALUES
(1, 15, '2026-05-15', 'Compra de nueva mercadería al sistema', b'1', 1),
(2, 20, '2026-05-15', 'Venta de mercadería', b'0', 1),
(3, 5, '2026-05-15', 'Compra de nueva mercadería al sistema', b'1', 2),
(4, 19, '2026-05-14', 'Compra de nueva mercadería al sistema', b'1', 3),
(5, 20, '2026-05-14', 'venta de mercadería', b'0', 3),
(6, 10, '2026-05-14', 'venta de mercadería', b'0', 3),
(7, 20, '2026-05-17', 'Compra de nueva mercadería al sistema', b'1', 4),
(8, 19, '2026-05-17', 'Compra de nueva mercadería al sistema', b'1', 5),
(9, 2, '2026-05-17', 'venta de mercadería', b'0', 5),
(10, 50, '2026-05-17', 'Compra de nueva mercadería al sistema', b'1', 6),
(11, 19, '2026-05-17', 'Compra de nueva mercadería al sistema', b'1', 7),
(12, 19, '2026-05-17', 'venta de mercadería', b'0', 8),
(13, 55, '2026-05-17', 'venta de mercadería', b'0', 9),
(14, 30, '2026-05-18', 'venta de mercadería', b'0', 10),
(15, 30, '2026-05-18', 'Compra de nueva mercadería al sistema', b'1', 11),
(16, 19, '2026-05-18', 'Compra de nueva mercadería al sistema', b'1', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock`
--

CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `minimo_para_reposicion` int(11) NOT NULL,
  `producto_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`id`, `cantidad`, `minimo_para_reposicion`, `producto_id`) VALUES
(1, 95, 10, 1),
(2, 120, 10, 2),
(3, 89, 10, 3),
(4, 80, 10, 4),
(5, 97, 10, 5),
(6, 135, 10, 6),
(7, 124, 10, 7),
(8, 93, 10, 8),
(9, 47, 10, 9),
(10, 15, 10, 10),
(11, 125, 10, 11);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtjt58m2rg292xlprh0ntajk54` (`stock_id`);

--
-- Indices de la tabla `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `movimientos`
--
ALTER TABLE `movimientos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `stock`
--
ALTER TABLE `stock`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `FKtjt58m2rg292xlprh0ntajk54` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
