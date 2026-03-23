package com.taskflow.cakeapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taskflow.cakeapp.R
import com.taskflow.cakeapp.data.OrderUiState
import com.taskflow.cakeapp.screens.components.FormattedPriceLabel

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )
    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    val newOrder = stringResource(R.string.new_cupcake_order)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(R.string.quantity).uppercase())
            Text(text = numberOfCupcakes, fontWeight = FontWeight.Bold)
            HorizontalDivider(thickness = 1.dp)
            Text(stringResource(R.string.flavor).uppercase())
            Text(text = orderUiState.flavor, fontWeight = FontWeight.Bold)
            HorizontalDivider(thickness = 1.dp)
            Text(stringResource(R.string.pickup_date).uppercase())
            Text(text = orderUiState.date, fontWeight = FontWeight.Bold)
            HorizontalDivider(thickness = 1.dp)
            FormattedPriceLabel(
                subtotal = orderUiState.price,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = { onSendButtonClicked(newOrder, orderSummary) }
            ) {
                Text(stringResource(R.string.send))
            }
        }
    }
}

@Preview
@Composable
fun OrderSummaryPreview() {
    OrderSummaryScreen(
        orderUiState = OrderUiState(
            quantity = 6,
            flavor = "Vanilla",
            date = "Fri Nov 17",
            price = "$12.00"
        ),
        onCancelButtonClicked = {},
        onSendButtonClicked = { _, _ -> },
        modifier = Modifier.fillMaxHeight()
    )
}