-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2016 at 10:08 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webpro2`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `system_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `leave_form`
--

CREATE TABLE `leave_form` (
  `leave_id` int(11) NOT NULL,
  `leave_created_at` datetime NOT NULL,
  `leave_start_at` date NOT NULL,
  `leave_end_at` date NOT NULL,
  `leave_year` year(4) DEFAULT NULL,
  `leave_total_date` date NOT NULL,
  `leave_contact` varchar(255) DEFAULT NULL,
  `leave_type` int(11) NOT NULL,
  `leave_status` varchar(45) NOT NULL,
  `approve_comment` varchar(255) DEFAULT NULL,
  `approve_date` datetime DEFAULT NULL,
  `approve_by` varchar(16) DEFAULT NULL,
  `wife_name` varchar(255) DEFAULT NULL,
  `give_birth_date` date DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `medical_certificate` varchar(255) DEFAULT NULL,
  `username` varchar(16) NOT NULL,
  `section_id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(1) NOT NULL,
  `role_name` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'teacher'),
(2, 'staff'),
(3, 'student'),
(4, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `section_id` int(2) NOT NULL,
  `th_name` varchar(60) NOT NULL,
  `en_name` varchar(60) DEFAULT NULL,
  `manager` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`section_id`, `th_name`, `en_name`, `manager`) VALUES
(1, 'ส่วนสนับสนุนวิชาการ', NULL, 'Nopporn'),
(2, 'งานบริหารงานทั่วไป', NULL, 'Wanida'),
(3, 'งานประชาสัมพันธ์', NULL, 'Boonprasert'),
(4, 'งานประกันคุณภาพการศึกษา', NULL, 'Boonprasert'),
(5, 'งานแผนงาน', NULL, 'Wanida'),
(6, 'งานบริหารทรัพยากรบุคคล', NULL, 'Wanida'),
(7, 'งานการเงินและบัญชี', NULL, 'Wanida'),
(8, 'งานพัสดุ', NULL, 'Wanida'),
(9, 'งานอาคารสถานที่ บำรุงรักษาและยานพาหนะ', NULL, 'Anuntapat'),
(10, 'งานบริหารการวิจัย', NULL, 'Olarn'),
(11, 'งานบริการวิชาการแก่สังคม', NULL, 'Olarn'),
(12, 'งานบริหารวิชาการและบัณฑิตศึกษา', NULL, 'Worapoj'),
(13, 'งานบริการการเรียนการสอน', NULL, 'Kanokwan'),
(14, 'งานกิจการนักศึกษา', NULL, 'Anuntapat'),
(15, 'งานเทคโนโลยีสารสนเทศ', NULL, 'Panwit'),
(16, 'งานวิชาการ', NULL, 'Nopporn');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `th_name` varchar(60) NOT NULL,
  `en_name` varchar(60) NOT NULL,
  `section_id` int(2) DEFAULT '1',
  `mobile` varchar(16) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `username`, `th_name`, `en_name`, `section_id`, `mobile`, `email`) VALUES
('21111', 'Wanida', 'นางวนิดา  ศุภเจียรพันธ์', 'Mrs. Wanida  Suphachearapan', 1, NULL, NULL),
('21112', 'Supa', 'นางสุภา  พิมพ์สวัสดิ์', 'Mrs. Supa Pimswat', 8, NULL, NULL),
('21113', 'Boonchuay', 'นางบุญช่วย  ชาติทอง', 'Mrs. Boonchuay Chattong', 5, NULL, NULL),
('21114', 'Pichitra', 'นางสาวพิจิตรา  สุวรรณศรี', 'Miss Pichitra Suwansri', 7, NULL, NULL),
('21115', 'Apichat', 'ว่าที่ ร.ต.อภิชาต  ฉายะรถี', 'Mr. Apichat Chayarati', 9, NULL, NULL),
('21116', 'Chidapa', 'นางสาวจิดาภา  เผ่าแย้มทวีป', 'Miss Chidapa Paoyaemtaweep', 2, NULL, NULL),
('21117', 'Wancharawan', 'นางวัชรวรรณ  นิวิฐจรรยงค์', 'Mrs. Wancharawan Nivichanyong', 8, NULL, NULL),
('21118', 'Ritisak', 'นายฤทธิศักดิ์  ห้อยสังวาลย์', 'Mr. Ritisak Hoisangwarn', 9, NULL, NULL),
('21119', 'Nitsawara', 'นางสาวณิศวรา  จันทร์เพ็ชร', 'Miss Nitsawara Janphet', 12, NULL, NULL),
('21120', 'Chalermkiat', 'นายเฉลิมเกียรติ  พวงมาลัย', 'Mr. Chalermkiat Puangmalai', 9, NULL, NULL),
('21121', 'Prateep', 'นายประทีป  อินทร์เขียว', 'Mr. Prateep In-Khiaw', 14, NULL, NULL),
('21122', 'Apinya', 'นางสาวอภิญญา  ปิ่นเงิน', 'Miss Apinya Pin-ngern', 5, NULL, NULL),
('21123', 'Vipada', 'นางสาววิภาดา  ศิลา', 'Miss Vipada Sila', 6, NULL, NULL),
('21124', 'Nutnaree', 'นางสาวณัฐนรี  เลิศไพรัตน์', 'Miss Nutnaree Lertpairat', 12, NULL, NULL),
('21125', 'Nuttawut', 'นายณัฐวุฒิ  คุมภะสาโน', 'Mr. Nuttawut Cumpasano', 3, NULL, NULL),
('21126', 'Jirayu', 'นายจิรายุ  ชมภูนุช', 'Mr. Jirayu Chompunut', 9, NULL, NULL),
('21127', 'Thanatjade', 'นายธนัชเจตน์  บุญเชิด', 'Mr. Thanatjade Booncherd', 12, NULL, NULL),
('21128', 'Phanlop', 'นายพัลลภ  จ้อยรักษา', 'Mr. Phanlop Joyraksa', 9, NULL, NULL),
('21129', 'Kamonnat', 'นางสาวกมนนัทธ์  ชื้นสกุล', 'Miss Kamonnat Chuensakun', 13, NULL, NULL),
('21130', 'Rattana', 'นางสาวรัตนา   วรผลึก', 'Miss Rattana Wolrapaluk', 12, NULL, NULL),
('21131', 'Oranuch', 'นางสาวอรนุช  เนียมกล่ำ', 'Miss Oranuch Nieamklam', 13, NULL, NULL),
('21132', 'Prayoug', 'นายประยงค์  สูตถิพันธ์', 'Mr. Prayoug Soottipan', 3, NULL, NULL),
('21133', 'Nutcharee', 'นางสาวนุชรี  ดำชมทรัพย์', 'Miss Nutcharee Damchomsap', 11, NULL, NULL),
('21134', 'Vimolluck', 'นางวิมลลักษณ์ เทียนจิ้ว', 'Mrs. Vimolluck Thianjew', 10, NULL, NULL),
('21135', 'Wipavan', 'นางสาววิภาวรรณ  มาลัย', 'Miss Wipavan Malai', 7, NULL, NULL),
('21136', 'Montree', 'นายมนตรี กิ่งแก้ว', 'Mr. Montree Kingkaew', 15, NULL, NULL),
('21137', 'Thanabordee', 'จ่าสิบเอกธนบดี  ข่ายม่าน', 'Mr. Thanabordee Khaimarn', 15, NULL, NULL),
('21138', 'Vooranuns', 'นางวรณันส์  สกุลวงศ์หิรัญ', 'Mrs. Vooranuns Skulwongheran', 4, NULL, NULL),
('21139', 'Daraka', 'นางดาระกา  ศิริสันติสัมฤทธิ์', 'Mrs. Daraka Sirisantisumrid', 2, NULL, NULL),
('21140', 'Sompong', 'นายสมพงษ์  แสนชา', 'Mr. Sompong Sancha', 15, NULL, NULL),
('21141', 'Withchayada', 'นางสาววิชญดา  วรกาญจนานนท์', 'Miss Withchayada Vorakangananon', 14, NULL, NULL),
('21142', 'Rungtiwa', 'นางสาวรุ่งทิวา  กิจเจริญ', 'Miss Rungtiwa Kitchareon', 6, NULL, NULL),
('21143', 'Pisnicha', 'นางสาวพิศน์ณิชา  กุลปฐวีวัฒน์', 'Miss Pisnicha Kulpathaweewat', 12, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(1) NOT NULL,
  `status_name` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_name`) VALUES
(1, 'พนักงาน'),
(2, 'ข้าราชการ'),
(3, '');

-- --------------------------------------------------------

--
-- Table structure for table `system`
--

CREATE TABLE `system` (
  `system_id` int(2) NOT NULL,
  `th_name` varchar(60) DEFAULT NULL,
  `en_name` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `system`
--

INSERT INTO `system` (`system_id`, `th_name`, `en_name`) VALUES
(1, 'ระบบประเมิน', NULL),
(2, 'ระบบเช็คชื่อ', NULL),
(3, 'ระบบลางาน', NULL),
(4, 'ระบบจัดตารางเรียนตารางสอน', NULL),
(5, 'ระบบคุมสอบ', NULL),
(6, 'ระบบผลงานของนักศึกษา', NULL),
(7, 'ระบบศิษย์เก่า', NULL),
(8, 'ระบบจองห้อง', NULL),
(9, 'ระบบคำร้องทั่วไป', NULL),
(10, 'ระบบขออนุมัตอบรม', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` varchar(16) NOT NULL,
  `username` varchar(16) NOT NULL,
  `th_prename` varchar(16) NOT NULL,
  `th_name` varchar(60) NOT NULL,
  `en_prename` varchar(16) DEFAULT NULL,
  `en_name` varchar(60) DEFAULT NULL,
  `status_id` int(1) DEFAULT '1',
  `mobile` varchar(16) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `username`, `th_prename`, `th_name`, `en_prename`, `en_name`, `status_id`, `mobile`, `email`) VALUES
('11111', 'Chanboon', 'รศ.ดร.', 'จันทร์บูรณ์  สถิตวิริยวงศ์', 'Assoc.Prof.Dr.', 'Chanboon  Sathitwiriyawong', 1, NULL, NULL),
('11112', 'Akharin', 'ผศ.', 'อัครินทร์  คุณกิตติ', 'Asst.Prof.', 'Akharin  Khunkitti', 1, NULL, NULL),
('11113', 'Arit', 'รศ. ดร.', 'อาริต  ธรรมโน', 'Assoc.Prof.Dr.', 'Arit  Thammano', 2, NULL, NULL),
('11114', 'Worapoj', 'รศ.ดร.', 'วรพจน์  กรีสุระเดช', 'Assoc.Prof.Dr.', 'Worapoj  Kreesuradej', 1, NULL, NULL),
('11115', 'Chotipat', 'รศ. ดร.', 'โชติพัชร์  ภรณวลัย', 'Assoc.Prof.Dr.', 'Chotipat  Pornavalai', 2, NULL, NULL),
('11116', 'Nopporn', 'รศ. ดร.', 'นพพร  โชติกกำธร', 'Assoc.Prof.Dr.', 'Nopporn  Chotikamthorn', 1, NULL, NULL),
('11117', 'Pattarachai', 'ผศ. ดร.', 'ภัทรชัย  ลลิตโรจน์วงศ์', 'Asst.Prof.Dr.', 'Pattarachai  Lalitrojwong', 2, NULL, NULL),
('11118', 'Ponrudee', 'รศ. ดร.', 'พรฤดี  เนติโสภากุล', 'Assoc.Preof.Dr.', 'Ponrudee  Netisopakul', 1, NULL, NULL),
('11119', 'Srinual', 'อาจารย์', 'ศรีนวล  นลินทิพยวงศ์', 'Miss.', 'Srinual  Nalintiphyvong', 1, NULL, NULL),
('11120', 'Olarn', 'ผศ.ดร.', 'โอฬาร  วงศ์วิรัตน์', 'Asst.Prof.Dr.', 'Olarn  Wongwirat', 1, NULL, NULL),
('11121', 'Warune', 'อาจารย์', 'วารุนี  บัววิรัตน์', 'Mrs.', 'Warune  Buavirat', 1, NULL, NULL),
('11122', 'Pattanapong', 'อาจารย์', 'พัฒนพงษ์  ฉันทมิตรโอภาส', 'Mr.', 'Pattanapong  Chantamit-O-Pas', 1, NULL, NULL),
('11123', 'Sumet', 'ผศ.ดร.', 'สุเมธ  ประภาวัต', 'Asst.Prof.Dr.', 'Sumet  Prabhavat', 1, NULL, NULL),
('11124', 'Anuntapat', 'อาจารย์', 'อนันตพัฒน์  อนันตชัย', 'Mr.', 'Anuntapat  Anuntachai', 1, NULL, NULL),
('11125', 'Boonprasert', 'ผศ.ดร.', 'บุญประเสริฐ  สุรักษ์รัตนสกุล', 'Asst.Prof.Dr.', 'Boonprasert  Surakratanaskul', 1, NULL, NULL),
('11126', 'Lapas', 'ดร.', 'ลภัส  ประดิษฐ์ทีศนีย์', 'Dr.', 'Lapas  Pradittasnee', 1, NULL, NULL),
('11127', 'Supannada', 'อาจารย์', 'สุพัณณดา  โชติพันธ์', 'Miss.', 'Supannada Chotipant', 1, NULL, NULL),
('11128', 'Thanisa', 'ผศ.ดร.', 'ธนิศา  นุ่มนนท์', 'Asst.Prof.Dr.', 'Thanisa  Numnonda', 1, NULL, NULL),
('11129', 'Natapon', 'ผศ.ดร.', 'ณฐพล   พันธุวงศ์', 'Asst.Prof.Dr.', 'Natapon  Pantuwong', 1, NULL, NULL),
('11130', 'Somkiat', 'ผศ.ดร.', 'สมเกียรติ  วังศิริพิทักษ์', 'Asst.Prof.Dr.', 'Somkiat  Wangsiripitak', 1, NULL, NULL),
('11131', 'Nol', 'ดร.', 'นล  เปรมัษเฐียร', 'Dr.', 'Nol  Premasathian', 1, NULL, NULL),
('11132', 'Sooksan', 'รศ.ดร.', 'สุขสันต์  พาณิชพาพิบูล', 'Assoc.Prof.Dr.', 'Sooksan  Panichpapiboon', 1, NULL, NULL),
('11133', 'Panwit', 'ผศ.ดร.', 'ปานวิทย์  ธุวะนุติ', 'Asst.Prof.Dr.', 'Panwit  Tuwanut', 1, NULL, NULL),
('11134', 'Supawan', 'ดร.', 'สุภวรรณ  อันนันหนับ', 'Dr.', 'Supawan  Annanab', 1, NULL, NULL),
('11135', 'Kitsuchart', 'ผศ.ดร.', 'กิติ์สุชาต  พสุภา', 'Asst.Prof.Dr.', 'Kitsuchart  Pasupa', 1, NULL, NULL),
('11136', 'Kuntpong', 'ผศ.ดร.', 'กันต์พงษ์  วรรัตน์ปัญญา', 'Asst.Prof.Dr.', 'Kuntpong  Woraratpanya', 1, NULL, NULL),
('11137', 'Supakit', 'ผศ.ดร.', 'สุภกิจ  นุตยะสกุล', 'Asst.Prof.Dr.', 'Supakit  Nootyasakool', 1, NULL, NULL),
('11138', 'Manop', 'ผศ.ดร.', 'มานพ  พันธ์โคกกรวด', 'Asst.Prof.Dr.', 'Manop  Phankokkruad', 1, NULL, NULL),
('11139', 'Teerapong', 'ผศ.ดร.', 'ธีรพงศ์  ลีลานุภาพ', 'Asst.Prof.Dr.', 'Teerapong  Leelanupab', 1, NULL, NULL),
('11140', 'Kanokwan', 'ดร.', 'กนกวรรณ  อัจฉริยะชาญวณิช', 'Dr.', 'Kanokwan  Atchariyachanvanich', 1, NULL, NULL),
('11141', 'Phitchayaphong', 'อาจารย์', 'พิชญพงษ์  ตันติกุล', 'Mr.', 'Phitchayaphong  Tantikul', 1, NULL, NULL),
('11142', 'Bundit', 'ดร.', 'บัณฑิต  ฐานะโสภณ', 'Dr.', 'Bundit  Thanasopon', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL DEFAULT 'itkmitl',
  `role_id` int(1) NOT NULL,
  `section_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `role_id`, `section_id`) VALUES
('57070001', 'itkmitl', 3, NULL),
('57070002', 'itkmitl', 3, NULL),
('57070004', 'itkmitl', 3, NULL),
('57070005', 'itkmitl', 3, NULL),
('57070006', 'itkmitl', 3, NULL),
('57070007', 'itkmitl', 3, NULL),
('57070008', 'itkmitl', 3, NULL),
('57070009', 'itkmitl', 3, NULL),
('57070010', 'itkmitl', 3, NULL),
('57070011', 'itkmitl', 3, NULL),
('57070012', 'itkmitl', 3, NULL),
('57070013', 'itkmitl', 3, NULL),
('57070014', 'itkmitl', 3, NULL),
('57070015', 'itkmitl', 3, NULL),
('57070017', 'itkmitl', 3, NULL),
('57070018', 'itkmitl', 3, NULL),
('57070019', 'itkmitl', 3, NULL),
('57070020', 'itkmitl', 3, NULL),
('57070021', 'itkmitl', 3, NULL),
('57070022', 'itkmitl', 3, NULL),
('57070024', 'itkmitl', 3, NULL),
('57070025', 'itkmitl', 3, NULL),
('57070026', 'itkmitl', 3, NULL),
('57070027', 'itkmitl', 3, NULL),
('57070028', 'itkmitl', 3, NULL),
('57070029', 'itkmitl', 3, NULL),
('57070030', 'itkmitl', 3, NULL),
('57070031', 'itkmitl', 3, NULL),
('57070033', 'itkmitl', 3, NULL),
('57070035', 'itkmitl', 3, NULL),
('57070037', 'itkmitl', 3, NULL),
('57070038', 'itkmitl', 3, NULL),
('57070039', 'itkmitl', 3, NULL),
('57070040', 'itkmitl', 3, NULL),
('57070041', 'itkmitl', 3, NULL),
('57070042', 'itkmitl', 3, NULL),
('57070044', 'itkmitl', 3, NULL),
('57070045', 'itkmitl', 3, NULL),
('57070046', 'itkmitl', 3, NULL),
('57070047', 'itkmitl', 3, NULL),
('57070048', 'itkmitl', 3, NULL),
('57070049', 'itkmitl', 3, NULL),
('57070050', 'itkmitl', 3, NULL),
('57070051', 'itkmitl', 3, NULL),
('57070052', 'itkmitl', 3, NULL),
('57070053', 'itkmitl', 3, NULL),
('57070054', 'itkmitl', 3, NULL),
('57070055', 'itkmitl', 3, NULL),
('57070056', 'itkmitl', 3, NULL),
('57070057', 'itkmitl', 3, NULL),
('57070058', 'itkmitl', 3, NULL),
('57070059', 'itkmitl', 3, NULL),
('57070060', 'itkmitl', 3, NULL),
('57070061', 'itkmitl', 3, NULL),
('57070062', 'itkmitl', 3, NULL),
('57070063', 'itkmitl', 3, NULL),
('57070064', 'itkmitl', 3, NULL),
('57070065', 'itkmitl', 3, NULL),
('57070066', 'itkmitl', 3, NULL),
('57070067', 'itkmitl', 3, NULL),
('57070068', 'itkmitl', 3, NULL),
('57070071', 'itkmitl', 3, NULL),
('57070073', 'itkmitl', 3, NULL),
('57070074', 'itkmitl', 3, NULL),
('57070075', 'itkmitl', 3, NULL),
('57070076', 'itkmitl', 3, NULL),
('57070077', 'itkmitl', 3, NULL),
('57070078', 'itkmitl', 3, NULL),
('57070079', 'itkmitl', 3, NULL),
('57070080', 'itkmitl', 3, NULL),
('57070082', 'itkmitl', 3, NULL),
('57070083', 'itkmitl', 3, NULL),
('57070084', 'itkmitl', 3, NULL),
('57070085', 'itkmitl', 3, NULL),
('57070086', 'itkmitl', 3, NULL),
('57070087', 'itkmitl', 3, NULL),
('57070088', 'itkmitl', 3, NULL),
('57070091', 'itkmitl', 3, NULL),
('57070093', 'itkmitl', 3, NULL),
('57070094', 'itkmitl', 3, NULL),
('57070096', 'itkmitl', 3, NULL),
('57070098', 'itkmitl', 3, NULL),
('57070099', 'itkmitl', 3, NULL),
('57070100', 'itkmitl', 3, NULL),
('57070101', 'itkmitl', 3, NULL),
('57070102', 'itkmitl', 3, NULL),
('57070103', 'itkmitl', 3, NULL),
('57070104', 'itkmitl', 3, NULL),
('57070105', 'itkmitl', 3, NULL),
('57070106', 'itkmitl', 3, NULL),
('57070107', 'itkmitl', 3, NULL),
('57070108', 'itkmitl', 3, NULL),
('57070109', 'itkmitl', 3, NULL),
('57070110', 'itkmitl', 3, NULL),
('57070111', 'itkmitl', 3, NULL),
('57070112', 'itkmitl', 3, NULL),
('57070113', 'itkmitl', 3, NULL),
('57070114', 'itkmitl', 3, NULL),
('57070115', 'itkmitl', 3, NULL),
('57070116', 'itkmitl', 3, NULL),
('57070117', 'itkmitl', 3, NULL),
('57070118', 'itkmitl', 3, NULL),
('57070119', 'itkmitl', 3, NULL),
('57070120', 'itkmitl', 3, NULL),
('57070121', 'itkmitl', 3, NULL),
('57070122', 'itkmitl', 3, NULL),
('57070123', 'itkmitl', 3, NULL),
('57070124', 'itkmitl', 3, NULL),
('57070125', 'itkmitl', 3, NULL),
('57070126', 'itkmitl', 3, NULL),
('57070127', 'itkmitl', 3, NULL),
('57070128', 'itkmitl', 3, NULL),
('57070129', 'itkmitl', 3, NULL),
('57070130', 'itkmitl', 3, NULL),
('57070131', 'itkmitl', 3, NULL),
('57070132', 'itkmitl', 3, NULL),
('57070133', 'itkmitl', 3, NULL),
('57070134', 'itkmitl', 3, NULL),
('57070135', 'itkmitl', 3, NULL),
('57070136', 'itkmitl', 3, NULL),
('57070137', 'itkmitl', 3, NULL),
('57070138', 'itkmitl', 3, NULL),
('57070139', 'itkmitl', 3, NULL),
('57070140', 'itkmitl', 3, NULL),
('57070141', 'itkmitl', 3, NULL),
('57070142', 'itkmitl', 3, NULL),
('57070143', 'itkmitl', 3, NULL),
('57070144', 'itkmitl', 3, NULL),
('57070145', 'itkmitl', 3, NULL),
('57070146', 'itkmitl', 3, NULL),
('57070148', 'itkmitl', 3, NULL),
('57070149', 'itkmitl', 3, NULL),
('57070150', 'itkmitl', 3, NULL),
('admin', 'admin', 4, NULL),
('Akharin', 'itkmitl', 1, 16),
('Anuntapat', 'itkmitl', 1, 16),
('Apichat', 'itkmitl', 2, NULL),
('Apinya', 'itkmitl', 2, 14),
('Arit', 'itkmitl', 1, 16),
('Boonchuay', 'itkmitl', 2, 13),
('Boonprasert', 'itkmitl', 1, 16),
('Bundit', 'itkmitl', 1, 16),
('Chalermkiat', 'itkmitl', 2, 12),
('Chanboon', 'itkmitl', 1, 16),
('Chidapa', 'itkmitl', 2, 1),
('Chotipat', 'itkmitl', 1, 16),
('Daraka', 'itkmitl', 2, 11),
('Jirayu', 'itkmitl', 2, 10),
('Kamonnat', 'itkmitl', 2, NULL),
('Kanokwan', 'itkmitl', 1, 16),
('Kitsuchart', 'itkmitl', 1, 16),
('Kuntpong', 'itkmitl', 1, 16),
('Lapas', 'itkmitl', 1, 16),
('Manop', 'itkmitl', 1, 16),
('Montree', 'itkmitl', 2, 15),
('Natapon', 'itkmitl', 1, 16),
('Nitsawara', 'itkmitl', 2, NULL),
('Nol', 'itkmitl', 1, 16),
('Nopporn', 'itkmitl', 1, 16),
('Nutcharee', 'itkmitl', 2, NULL),
('Nutnaree', 'itkmitl', 2, NULL),
('Nuttawut', 'itkmitl', 2, NULL),
('Olarn', 'itkmitl', 1, 16),
('Oranuch', 'itkmitl', 2, NULL),
('Panwit', 'itkmitl', 1, 16),
('Pattanapong', 'itkmitl', 1, 16),
('Pattarachai', 'itkmitl', 1, 16),
('Phanlop', 'itkmitl', 2, NULL),
('Phitchayaphong', 'itkmitl', 1, 16),
('Pichitra', 'itkmitl', 2, NULL),
('Pisnicha', 'itkmitl', 2, NULL),
('Ponrudee', 'itkmitl', 1, 16),
('Prateep', 'itkmitl', 2, NULL),
('Prayoug', 'itkmitl', 2, NULL),
('Rattana', 'itkmitl', 2, NULL),
('Ritisak', 'itkmitl', 2, NULL),
('Rungtiwa', 'itkmitl', 2, NULL),
('Somkiat', 'itkmitl', 1, 16),
('Sompong', 'itkmitl', 2, 15),
('Sooksan', 'itkmitl', 1, 16),
('Srinual', 'itkmitl', 1, 16),
('Sumet', 'itkmitl', 1, 16),
('Supa', 'itkmitl', 2, NULL),
('Supakit', 'itkmitl', 1, 16),
('Supannada', 'itkmitl', 1, 16),
('Supawan', 'itkmitl', 1, 16),
('Teerapong', 'itkmitl', 1, 16),
('Thanabordee', 'itkmitl', 2, 15),
('Thanatjade', 'itkmitl', 2, NULL),
('Thanisa', 'itkmitl', 1, 16),
('Vimolluck', 'itkmitl', 2, NULL),
('Vipada', 'itkmitl', 2, NULL),
('Vooranuns', 'itkmitl', 2, NULL),
('Wancharawan', 'itkmitl', 2, NULL),
('Wanida', 'itkmitl', 2, NULL),
('Warune', 'itkmitl', 1, 16),
('Wipavan', 'itkmitl', 2, NULL),
('Withchayada', 'itkmitl', 2, NULL),
('Worapoj', 'itkmitl', 1, 16);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD KEY `admin_admin_username_idx` (`username`),
  ADD KEY `admin_system_id_idx` (`system_id`);

--
-- Indexes for table `leave_form`
--
ALTER TABLE `leave_form`
  ADD PRIMARY KEY (`leave_id`),
  ADD KEY `fk_leave_form_user1_idx` (`username`),
  ADD KEY `fk_leave_form_section1_idx` (`section_id`),
  ADD KEY `fk_leave_form_user2_idx` (`approve_by`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`section_id`),
  ADD KEY `user_fk_idx` (`manager`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`),
  ADD KEY `staff_staff_id_idx` (`username`),
  ADD KEY `staff_section_id_idx` (`section_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `system`
--
ALTER TABLE `system`
  ADD PRIMARY KEY (`system_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`),
  ADD KEY `teacher_teacher_username_idx` (`username`),
  ADD KEY `teacher_status_id_idx` (`status_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD KEY `role_role_id_idx` (`role_id`),
  ADD KEY `section_section_id_idx` (`section_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `leave_form`
--
ALTER TABLE `leave_form`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `section_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_admin_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `admin_system_id` FOREIGN KEY (`system_id`) REFERENCES `system` (`system_id`) ON UPDATE NO ACTION;

--
-- Constraints for table `leave_form`
--
ALTER TABLE `leave_form`
  ADD CONSTRAINT `fk_leave_form_section1` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_leave_form_user1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_leave_form_user2` FOREIGN KEY (`approve_by`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `section`
--
ALTER TABLE `section`
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`manager`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FK_n5ib031h2ipdsj507srabt3kf` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `staff_section_id` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`) ON UPDATE NO ACTION;

--
-- Constraints for table `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `teacher_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `teacher_username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `section_section_id` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
