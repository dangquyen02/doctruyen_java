<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Đọc truyện Online - Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/adminstyles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="AdminTrangChuController"> <img width="50px" alt="" src="img-truyen/logo-java.png" style="width: 140px; height: 60px"> </a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Tìm kiếm..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Cài đặt</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="AdminDangXuatController">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="AdminTrangChuController">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Tổng quát
                            </a>
                            <div class="sb-sidenav-menu-heading">Giao diện</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Quản lý truyện
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <div>
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="AdminNguoiDungController">Người dùng</a>
                                            <a class="nav-link" href="AdminTruyenController">Truyện</a>
                                            <a class="nav-link" href="AdminTacGiaController">Tác giả</a>
                                            <a class="nav-link" href="AdminTheLoaiController">Thể loại</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                        </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h2 class="mt-4">Quản lý tác giả</h2>
                        <form action="AdminTacGiaController" method="get" class="form-inline d-flex justify-content-between align-items-center flex-wrap">
						    <div class="form-group">
						        <label for="txtmatacgia">Mã tác giả:</label>
						        <input name="txtmatacgia"" type="text" value="${sessionScope.selectmatg }" class="form-control mt-1" placeholder="Nhập mã tác giả">
						    </div>
						    <div class="form-group">
						        <label for="txttentacgia">Tên tác giả:</label>
						        <input name="txttentacgia" type="text" value="${sessionScope.selectentg }" class="form-control mt-1" placeholder="Nhập tên tác giả">
						    </div>
						    <div class="form-group">
						        <label for="txtquequan">Quê quán:</label>
						        <input name="txtquequan" type="text" value="${sessionScope.selectque }" class="form-control mt-1" placeholder="Nhập quê quán">
						    </div>
						    <div class="w-100"></div>
						    <div class="form-group mt-2">
						        <input class="btn btn-secondary" name="butadd" type="submit" value="Thêm">
						        <input class="btn btn-secondary" name="butupdate" type="submit" value="Cập nhật">
						    </div>
						    <div class="form-group">
						    </div>
						</form>
					    <p class="text-danger mt-1 fw-bold">* Thêm tác giả không cần nhập mã tác giả</p>
                        <div class="card my-2">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Danh sách tác giả
                            </div>
                            <div class="card-body">
								<table class="table table-hover">
		                            <thead>
		                                <tr>
		                                    <th>Mã tác giả</th>
		                                    <th>Họ tên</th>
		                                    <th>Quê quán</th>
		                                    <th></th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                                <c:forEach items="${dstacgia}" var="tacgia">
										    <tr>
										        <td>${tacgia.getMatacgia()}</td>
										        <td><a class="text-decoration-none text-primary" href="AdminTacGiaController?selecttacgia=${tacgia.getMatacgia()}"> ${tacgia.getTentacgia()}</a></td>
										        <td>${tacgia.getQuequan()}</td>
										        <td><a class="text-decoration-none text-primary" href="AdminTacGiaController?mtg=${tacgia.getMatacgia()}&tab=xoa">Xóa</a> </td>
										    </tr>
										</c:forEach>
		                            </tbody>
		                        </table>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="mt-3 bg-dark text-white fw-bold fs-5 text-center py-3">
				   <p class="m-0">&copy; 2023 No CopyRight</p>
				   <p class="m-0">Mã sinh viên: 20T1020237</p>
				   <p class="m-0">Tên: Đặng Thị Quyên</p>
				   <p class="m-0">Email: <a class="text-decoration-none text-white" href="mailto:20t1020237@husc.edu.vn">20t1020237@husc.edu.vn</a></p>
				</footer>
			</div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
		<script src="js/thongke.js"></script>
</html>
