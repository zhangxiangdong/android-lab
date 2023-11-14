package me.zhang.laboratory.ui.navi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import me.zhang.laboratory.R

class NavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph =
            navController.createGraph(startDestination = NavRoutes.A) {
                fragment<AFragment>(NavRoutes.A) {
                    label = resources.getString(R.string.a)
                }
                fragment<BFragment>("${NavRoutes.B}/{${NavArguments.ID}}?${NavArguments.NAME}={${NavArguments.NAME}}&${NavArguments.AGE}={${NavArguments.AGE}}") {
                    label = resources.getString(R.string.b)
                    deepLink {
                        uriPattern =
                            "android-lab://zhang.me/${NavRoutes.B}/{${NavArguments.ID}}?${NavArguments.NAME}={${NavArguments.NAME}}&${NavArguments.AGE}={${NavArguments.AGE}}"
                        action = "android.intent.action.B_ACTION"
                        mimeType = "b/b"
                    }
                    argument(NavArguments.ID) {
                        type = NavType.IntType
                    }
                    argument(NavArguments.NAME) {
                        type = NavType.StringType
                    }
                    argument(NavArguments.AGE) {
                        type = NavType.IntType
                    }
                }
                fragment<CFragment>(NavRoutes.C) {
                    label = resources.getString(R.string.c)
                }
                fragment<DFragment>(NavRoutes.D) {
                    label = resources.getString(R.string.d)
                }
                activity(NavRoutes.X) {
                    label = getString(R.string.x)
                    // arguments, deepLinks...

                    activityClass = XActivity::class
                }
            }

//        setContent {
//            MyAppNavHost()
//        }
    }

    @Composable
    private fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = NavRoutes.A
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavRoutes.A) {
                DestinationA(
                    onNavigateToB = {
                        // Navigate to the "b" destination
                        navController.navigate(NavRoutes.B)
                    },
                )
            }
            composable(NavRoutes.B) {
                DestinationB(onNavigateToC = {
                    // Navigate to the "c" destination
                    navController.navigate(NavRoutes.C)
                })
            }
            composable(NavRoutes.C) {
                DestinationC(onNavigateToD = {
                    // Pop everything up to the "a" destination off the back stack before
                    // navigating to the "d" destination
                    navController.navigate(NavRoutes.D) {
                        popUpTo(NavRoutes.A) {
                            inclusive = true
                            saveState = true
                        }
                    }
                }, onNavigateBackToA = {
                    // Navigate back to the "a" destination
                    navController.popBackStack(NavRoutes.A, false)
//                    navController.popBackStack(R.id.AFragment, false) // Not work!
                })
            }
            composable(NavRoutes.D) { DestinationD(/* ... */) }
        }
    }

    @Composable
    private fun DestinationA(onNavigateToB: () -> Unit) {
        Column {
            Text("Current: A")
            Button(onClick = onNavigateToB) {
                Text("Jump to B")
            }
        }
    }

    @Composable
    private fun DestinationB(onNavigateToC: () -> Unit) {
        Column {
            Text("Current: B")
            Button(onClick = onNavigateToC) {
                Text("Jump to C")
            }
        }
    }

    @Composable
    private fun DestinationC(onNavigateToD: () -> Unit, onNavigateBackToA: () -> Unit) {
        Column {
            Text("Current: C")
            Button(onClick = onNavigateBackToA) {
                Text(text = "Back to A")
            }
            Button(onClick = onNavigateToD) {
                Text("Jump to D")
            }
        }
    }

    @Composable
    private fun DestinationD() {
        Text("D")
    }

    @Preview(showSystemUi = true)
    @Composable
    fun PreviewMyAppNavHost() {
        MyAppNavHost()
    }
}

object NavRoutes {
    const val A = "a"
    const val B = "b"
    const val C = "c"
    const val D = "d"
    const val X = "x"
}

object NavArguments {
    const val ID = "id"
    const val NAME = "name"
    const val AGE = "age"
}