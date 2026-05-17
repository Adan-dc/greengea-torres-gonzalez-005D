-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2026 a las 03:53:27
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
-- Base de datos: `db_servicios`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio_desde` int(11) NOT NULL,
  `tiempo_estimado_entrega` date DEFAULT NULL,
  `tipo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `codigo`, `descripcion`, `precio_desde`, `tiempo_estimado_entrega`, `tipo`) VALUES
(1, 'SERV-001', 'requiero hacer un catalogo sobre mi tienda de tecnología que tenga todos los productos y servicio que ofrecemos tanto de mantenimiento como de ventas de productos', 75000, '2026-06-15', 'Catalogos Digitales'),
(2, 'SERV-002', 'requiero un logo para mi empresa de servicios de seguridad como cámaras, alarmas, etc', 75000, '2026-06-15', 'Logos'),
(3, 'SERV-003', 'requiero una página web para una constructora llamada Constructora WEN donde podamos mostrarnos hacia el público ofreciendo nuestros servicios y contactos', 75000, '2026-06-15', 'Páginas Web'),
(4, 'SERV-004', 'Requiero unas tarjetas de presentación para mi empresa sobre capacitaciones en protección civil, que incluya mis redes sociales, mi contacto de correo, ubicación y teléfono', 75000, '2026-06-15', 'Diseño gráfico');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
