-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-01-2018 a las 17:26:22
-- Versión del servidor: 10.1.26-MariaDB
-- Versión de PHP: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `diagnostico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diagnostico`
--

CREATE TABLE `diagnostico` (
  `id` int(11) UNSIGNED NOT NULL,
  `texto` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `diagnostico`
--

INSERT INTO `diagnostico` (`id`, `texto`) VALUES
(2, 'Has envejecido'),
(3, 'Cortada en el craneo'),
(4, 'Cancer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(80) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `ciudad` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id`, `nombre`, `direccion`, `telefono`, `ciudad`, `email`) VALUES
(1, 'Luis', 'Alhambra 244', '33243421', 'Zapopan', 'luigi@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente_diagnosico`
--

CREATE TABLE `paciente_diagnosico` (
  `id_paciente` int(10) UNSIGNED NOT NULL,
  `id_diagnostico` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `signo`
--

CREATE TABLE `signo` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `signo`
--

INSERT INTO `signo` (`id`, `nombre`) VALUES
(7, 'Hinchazon'),
(8, 'Arrugas'),
(9, 'Enrojecimiento'),
(10, 'Sangrado'),
(12, 'Ojos Llorosos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `signo_diagnostico`
--

CREATE TABLE `signo_diagnostico` (
  `signo_id` int(10) UNSIGNED NOT NULL,
  `diagnostico_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `signo_diagnostico`
--

INSERT INTO `signo_diagnostico` (`signo_id`, `diagnostico_id`) VALUES
(8, 2),
(10, 3),
(7, 4),
(9, 4),
(12, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sintoma`
--

CREATE TABLE `sintoma` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sintoma`
--

INSERT INTO `sintoma` (`id`, `nombre`) VALUES
(1, 'Calentura'),
(2, 'Dolor de Cabeza'),
(3, 'Mareos'),
(4, 'Debilidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sintoma_diagnostico`
--

CREATE TABLE `sintoma_diagnostico` (
  `sintoma_id` int(10) UNSIGNED NOT NULL,
  `diagnostico_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sintoma_diagnostico`
--

INSERT INTO `sintoma_diagnostico` (`sintoma_id`, `diagnostico_id`) VALUES
(2, 3),
(2, 4),
(3, 4),
(4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento`
--

CREATE TABLE `tratamiento` (
  `id` int(10) UNSIGNED NOT NULL,
  `texto` varchar(45) NOT NULL,
  `diagnostico_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tratamiento`
--

INSERT INTO `tratamiento` (`id`, `texto`, `diagnostico_id`) VALUES
(2, 'Cirujía plastica.', 2),
(3, 'Quimioterapia', 4),
(4, 'Vendar la zona afectada', 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `diagnostico`
--
ALTER TABLE `diagnostico`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `paciente_diagnosico`
--
ALTER TABLE `paciente_diagnosico`
  ADD KEY `id_diagnostico` (`id_diagnostico`),
  ADD KEY `id_paciente` (`id_paciente`);

--
-- Indices de la tabla `signo`
--
ALTER TABLE `signo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `signo_diagnostico`
--
ALTER TABLE `signo_diagnostico`
  ADD KEY `signo_id` (`signo_id`),
  ADD KEY `diagnostico_id` (`diagnostico_id`);

--
-- Indices de la tabla `sintoma`
--
ALTER TABLE `sintoma`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sintoma_diagnostico`
--
ALTER TABLE `sintoma_diagnostico`
  ADD KEY `sintoma_id` (`sintoma_id`),
  ADD KEY `diagnostico_id` (`diagnostico_id`);

--
-- Indices de la tabla `tratamiento`
--
ALTER TABLE `tratamiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `diagnostico_id` (`diagnostico_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `diagnostico`
--
ALTER TABLE `diagnostico`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `signo`
--
ALTER TABLE `signo`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `sintoma`
--
ALTER TABLE `sintoma`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `tratamiento`
--
ALTER TABLE `tratamiento`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `paciente_diagnosico`
--
ALTER TABLE `paciente_diagnosico`
  ADD CONSTRAINT `paciente_diagnosico_ibfk_1` FOREIGN KEY (`id_diagnostico`) REFERENCES `diagnostico` (`id`),
  ADD CONSTRAINT `paciente_diagnosico_ibfk_2` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id`);

--
-- Filtros para la tabla `signo_diagnostico`
--
ALTER TABLE `signo_diagnostico`
  ADD CONSTRAINT `signo_diagnostico_ibfk_1` FOREIGN KEY (`signo_id`) REFERENCES `signo` (`id`),
  ADD CONSTRAINT `signo_diagnostico_ibfk_2` FOREIGN KEY (`diagnostico_id`) REFERENCES `diagnostico` (`id`);

--
-- Filtros para la tabla `sintoma_diagnostico`
--
ALTER TABLE `sintoma_diagnostico`
  ADD CONSTRAINT `sintoma_diagnostico_ibfk_1` FOREIGN KEY (`sintoma_id`) REFERENCES `sintoma` (`id`),
  ADD CONSTRAINT `sintoma_diagnostico_ibfk_2` FOREIGN KEY (`diagnostico_id`) REFERENCES `diagnostico` (`id`);

--
-- Filtros para la tabla `tratamiento`
--
ALTER TABLE `tratamiento`
  ADD CONSTRAINT `tratamiento_ibfk_1` FOREIGN KEY (`diagnostico_id`) REFERENCES `diagnostico` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
