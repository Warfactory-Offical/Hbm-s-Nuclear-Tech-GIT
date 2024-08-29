package com.hbm.items.meta;

public interface IMetaItemMaterial {

    default String getNameSnakeCase() {
        return toString().toLowerCase();
    }

    default String getNamePascalCase() {
        String[] parts = toString().toLowerCase().split("_");
        StringBuilder pascalCaseName = new StringBuilder();
        for (String part : parts) {
            pascalCaseName.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }
        return pascalCaseName.toString();
    }

}
