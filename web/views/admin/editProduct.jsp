<%-- 
    Document   : editProduct
    Created on : Mar 11, 2024, 5:36:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="edit-product-modal">Edit Fruit Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editProductForm" action="product?action=edit" method="POST" enctype="multipart/form-data">
                    <!--id-->
                    <div class="form-group" style="display: none">
                        <input type="text" class="form-control" id="idEditInput" name="id">
                    </div>
                    <!--Name-->
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="nameEditInput" name="name">
                        <div id="nameEditError" class="error"></div>
                    </div>
                    <!--Price-->
                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="text" class="form-control" id="priceEditInput" name="price">
                        <div id="priceEditError" class="error"></div>
                    </div>
                    <!--Quantity-->
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="text" class="form-control" id="quantityEditInput" name="quantity">
                        <div id="quantityEditError" class="error"></div>
                    </div>
                    <!--Category-->
                    <div class="form-group">
                        <label for="category">Category: </label>
                        <div class="input-group">
                            <select class="custom-select" id="categoryEditInput" name="category">
                                <c:forEach items="${listCategory}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button">Category</button>
                            </div>
                        </div>
                    </div>
                    <!--Image-->
                    <div class="form-group">
                        <label for="image">Image: </label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Upload</span>
                            </div>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="imageEdit" name="image"
                                       onchange="displayImage(this)">
                                <label class="custom-file-label">Choose file</label>
                            </div>
                        </div>
                        <img id="previewImage2" src="" alt="Preview"
                             style="display: none; max-width: 300px; max-height: 300px;">
                        <input type="hidden" id="currentImage" name="currentImage" value="">
                    </div>
                    <!--Description-->
                    <div class="form-group">
                        <label for="descriptionEditInput">Description:</label>
                        <textarea class="form-control" id="descriptionEdit" name="description"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" form="editProductForm"
                        onclick="validateForm()">Update</button>
            </div>
        </div>
    </div>
</div>

<script>
    function validateForm() {
        let name = $('#nameEditInput').val();
        let price = $('#priceEditInput').val();
        let quantity = $('#quantityEditInput').val();
        //xoá thông báo lỗi hiện tại
        $('.error').html('');
        if (name === '') {
            $('#nameEditError').html('Fruit names cannot be left blank');
        }
        if (price === '') {
            $('#priceEditError').html('The price of fruit cannot be left blank');
        } else if (!$.isNumeric(price) || parseFloat(price) < 0) {
            $('#priceEditError').html('The price of the fruit must be a number and cannot be less than 0');
        }
        if (quantity === '') {
            $('#quantityEditError').html('The quantity of fruits cannot be left blank');
        } else if (!$.isNumeric(quantity) || parseInt(price) < 0) {
            $('#priceEditError').html('The number of fruits must be a number and cannot be less than 0');
        }
        $('.error').each(function () {
            error += $(this).html();
        });
        if (error === '') {
            $('#editProductForm').submit();
        } else {
            event.preventDefault();
        }
    }
    function displayImage(input) {
        var previewImage = document.getElementById("previewImage2");
        var file = input.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = "block";
            reader.readAsDataURL(file);
        }
    }

    function editProduct(button) {
        let id = $(button).closest('tr').find('td[name="id"]').text().trim();
        let name = $(button).closest('tr').find('td[name="name"]').text().trim();
        let price = $(button).closest('tr').find('td[name="price"]').text().trim().split('$')[0];
        let quantity = $(button).closest('tr').find('td[name="quantity"]').text().trim();
        let categoryText = $(button).closest('tr').find('td[name="category"]').text().trim();
        let description = $(button).closest('tr').find('td[name="description"]').text().trim();
        let image = $(button).closest('tr').find('td[name="image"]').find('img').attr('src');
        console.log(image);
        $('#idEditInput').val(id);
        $('#nameEditInput').val(name);
        $('#priceEditInput').val(price);
        $('#quantityEditInput').val(quantity);
        //loop through category list to select the category
        $('#categoryEditInput option').each(function () {
            if ($(this).text() === categoryText) {
                $(this).prop('selected', true);
            }
        });
        $('#descriptionEdit').val(description);
        $('#previewImage2').attr('src', image);
        $('#previewImage2').css('display', 'block');
        $('#currentImage').val(image);
    }
</script>
