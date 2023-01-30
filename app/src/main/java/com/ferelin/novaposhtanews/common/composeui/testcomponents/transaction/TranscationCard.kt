package com.endava.budgetplanner.common.composeui.components.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

private const val TEXT_SECTION_WEIGHT = 0.65f
private const val TRANSACTION_VALUE_WEIGHT = 0.35f

@Composable
fun TransactionCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    value: String,
    color: Color
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colors.backgroundSecondary,
                shape = AppTheme.shapes.small
            )
            .padding(all = dimensionResource(id = R.dimen.transaction_card_inner_padding))
    ) {
        val (colorBoxRef, contentRef) = createRefs()
        val paddingFromColorBox =
            dimensionResource(id = R.dimen.transaction_card_padding_from_color)

        Box(
            modifier = Modifier
                .constrainAs(colorBoxRef) {
                    start.linkTo(parent.start)
                    top.linkTo(contentRef.top)
                    bottom.linkTo(contentRef.bottom)
                    height = Dimension.fillToConstraints
                }
                .width(dimensionResource(id = R.dimen.transaction_card_color_width))
                .background(
                    color = color,
                    shape = RoundedCornerShape(
                        dimensionResource(id = R.dimen.transaction_card_color_radius)
                    )
                )
        )
        Row(
            modifier = Modifier
                .constrainAs(contentRef) {
                    start.linkTo(colorBoxRef.end, margin = paddingFromColorBox)
                    top.linkTo(parent.top)
                    end.linkTo((parent.end))
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(TEXT_SECTION_WEIGHT)
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.regularBody,
                    color = AppTheme.colors.text1
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        style = AppTheme.typography.regularCaption1,
                        color = AppTheme.colors.text2
                    )
                }
            }
            Text(
                modifier = Modifier.weight(TRANSACTION_VALUE_WEIGHT),
                text = value,
                style = AppTheme.typography.boldCallout,
                color = AppTheme.colors.text1,
                textAlign = TextAlign.End
            )
        }
    }
}
