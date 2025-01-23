package com.hbm.items.meta;

public interface IMetaItemMaterial {

    default String getNameSnakeCase() {
        return toString().toLowerCase();
    }

    default String getNamePascalCase() {
        final String[] parts = toString().toLowerCase().split("_");
        final StringBuilder pascalCaseName = new StringBuilder();
        for (final String part : parts) {
            pascalCaseName.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1));
        }
        return pascalCaseName.toString();
    }

}
