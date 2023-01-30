package com.endava.budgetplanner.common.composeui.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.data.model.TransactionCategoriesState

@Composable
fun CategoriesRow(
    modifier: Modifier = Modifier,
    categoriesData: List<TransactionCategoriesState>,
    onClickCategory: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement =
        Arrangement.spacedBy(dimensionResource(id = R.dimen.categories_row_space)),
        modifier = modifier.background(AppTheme.colors.backgroundSecondary)
    ) {
        items(
            items = categoriesData,
            key = { category -> category.id }
        ) { category ->
            if (category.sum == 0) {
                EmptyCategoryBox(
                    categoryName = category.name,
                    backColor = category.backColor,
                    borderColor = category.borderColor,
                    onClickCategory = { onClickCategory(category.id) }
                )
            } else {
                CategoryBox(
                    categoryName = category.name,
                    backColor = category.backColor,
                    categorySum = category.sum,
                    currency = category.currency,
                    percent = category.percent,
                    onClickCategory = { onClickCategory(category.id) }
                )
            }
        }
    }
}
