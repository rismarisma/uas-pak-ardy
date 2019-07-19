-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2019 at 08:44 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `persewaan_tenda`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `kd_admin` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telephone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`kd_admin`, `nama`, `alamat`, `no_telephone`) VALUES
('A01', 'RISMA', 'BOYOLALI', '08578909767'),
('A02', 'HANIFAH', 'KARANGANYAR', '456789098'),
('A07', 'DIANA', 'TUBAN', '870908');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `kd_customer` char(5) NOT NULL,
  `nama_customer` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telephone` int(15) NOT NULL,
  `ktp` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`kd_customer`, `nama_customer`, `alamat`, `no_telephone`, `ktp`) VALUES
('C001', 'PUTRI', 'NGEMPLAK', 289880, 18080),
('C002', 'FATMA', 'TIPES', 198380, 339730),
('C007', 'BELA', 'JOGJA', 687979, 68878);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kode_kategori` varchar(5) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kode_kategori`, `nama_kategori`) VALUES
('K01', 'TENDA KUTUB'),
('K02', 'TENDA FRAME'),
('K03', 'TENDA PARTY KANOPI'),
('K07', 'TENDA PARTY HORE');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_login` char(5) NOT NULL,
  `kd_admin` char(10) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `level` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_login`, `kd_admin`, `username`, `password`, `level`) VALUES
('L001', 'A01', 'RISMA', 'RISMA', 'ADMIN'),
('L002', 'A02', 'HANIFAH', 'HANIFAH', 'KASIR');

-- --------------------------------------------------------

--
-- Table structure for table `persewaan`
--

CREATE TABLE `persewaan` (
  `kd_peminjam` varchar(10) NOT NULL,
  `kd_admin` varchar(10) NOT NULL,
  `kd_customer` varchar(10) NOT NULL,
  `kode_produk` varchar(10) NOT NULL,
  `jenis_tenda` varchar(15) NOT NULL,
  `harga_sewa` int(15) NOT NULL,
  `jumlah_sewa` int(5) NOT NULL,
  `tgl_persewaan` varchar(12) NOT NULL,
  `tgl_pengembalian` varchar(12) NOT NULL,
  `total` double NOT NULL,
  `bayar` double NOT NULL,
  `kembali` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `persewaan`
--

INSERT INTO `persewaan` (`kd_peminjam`, `kd_admin`, `kd_customer`, `kode_produk`, `jenis_tenda`, `harga_sewa`, `jumlah_sewa`, `tgl_persewaan`, `tgl_pengembalian`, `total`, `bayar`, `kembali`) VALUES
('M001', 'A01', 'C001', 'P01', 'TENDA KUTUB', 1500000, 2, '12/01/2017', '14/01/2017', 6000000, 6500000, 500000),
('M008', 'A02', 'C002', 'P02', 'TENDA FRAME', 2000000, 2, '14-05-2019', '16-05-2019', 4000000, 5000000, 1000000),
('P001', 'A01', 'C001', 'P01', 'TENDA KUTUB', 1500000, 2, '12-01-2019', '14-01-2019', 3000000, 5000000, 2000000);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `kode_produk` varchar(10) NOT NULL,
  `kode_kategori` varchar(10) NOT NULL,
  `jenis_tenda` varchar(30) NOT NULL,
  `harga_sewa` int(10) NOT NULL,
  `stok` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`kode_produk`, `kode_kategori`, `jenis_tenda`, `harga_sewa`, `stok`) VALUES
('P01', 'K01', 'TENDA KUTUB', 1500000, 7),
('P02', 'K02', 'TENDA FRAME', 2000000, 3),
('P07', 'K07', 'TENDA PARTY HORE', 1800000, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`kd_admin`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`kd_customer`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kode_kategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_login`),
  ADD KEY `kd_admin` (`kd_admin`),
  ADD KEY `kd_admin_2` (`kd_admin`);

--
-- Indexes for table `persewaan`
--
ALTER TABLE `persewaan`
  ADD PRIMARY KEY (`kd_peminjam`),
  ADD KEY `kd_admin` (`kd_admin`),
  ADD KEY `kd_customer` (`kd_customer`),
  ADD KEY `kode_produk` (`kode_produk`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode_produk`),
  ADD KEY `kode_kategori` (`kode_kategori`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`kd_admin`) REFERENCES `admin` (`kd_admin`);

--
-- Constraints for table `persewaan`
--
ALTER TABLE `persewaan`
  ADD CONSTRAINT `persewaan_ibfk_1` FOREIGN KEY (`kd_admin`) REFERENCES `admin` (`kd_admin`),
  ADD CONSTRAINT `persewaan_ibfk_2` FOREIGN KEY (`kd_customer`) REFERENCES `customer` (`kd_customer`),
  ADD CONSTRAINT `persewaan_ibfk_3` FOREIGN KEY (`kode_produk`) REFERENCES `produk` (`kode_produk`);

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`kode_kategori`) REFERENCES `kategori` (`kode_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
