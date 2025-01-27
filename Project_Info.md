# **Project Breakdown**

## **Set Up and Customization**
- Cloned the existing Spring Framework project from **GitLab** into **IntelliJ IDEA**.
- Customized the HTML user interface to reflect the store’s branding by including:
  - Store name
  - Product names
  - Part names.

## **Navigation and New Features**
- Added an **“About” page** to describe the store and its business.
- Included navigation links between the main screen and the “About” page.

## **Sample Inventory Setup**
- Created and added a **sample inventory** consisting of:
  - 5 products
  - 5 parts
- Implemented logic to only add the sample inventory if the inventory list is empty.

## **"Buy Now" Button**
- Added a **"Buy Now" button** for each product, enabling users to purchase an item.
- Configured the button to:
  - Decrement the product inventory by 1.
  - Display success or failure messages after the transaction.

## **Inventory Validation Enhancements**
- Added **minimum and maximum inventory fields** for parts:
  - Modified part forms to allow input of minimum and maximum values.
  - Updated the sample inventory to include these fields.
- Enforced rules to ensure inventory values remain between the minimum and maximum thresholds:
  - Displayed error messages for invalid inventory levels during part or product creation/updates.

## **Unit Testing**
- Added at least **two unit tests** in the `PartTest` class to validate:
  - Inventory cannot exceed the maximum.
  - Inventory cannot fall below the minimum.

## **Code Clean-Up**
- Removed unused classes and validators to ensure the project is clean, organized, and efficient.
