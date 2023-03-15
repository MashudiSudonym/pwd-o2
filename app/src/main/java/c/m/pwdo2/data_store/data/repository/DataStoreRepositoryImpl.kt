package c.m.pwdo2.data_store.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val dataStorePreferences: DataStore<Preferences>) :
    DataStoreRepository {
    override suspend fun setDlhBodOutlet(dlhBodOutlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "dlhBodOutlet")] = dlhBodOutlet
            }
        }
    }

    override suspend fun setDlhCodOutlet(dlhCodOutlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "dlhCodOutlet")] = dlhCodOutlet
            }
        }
    }

    override suspend fun setDlhNhThreeOutlet(dlhNhThreeOutlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "dlhNhThreeOutlet")] = dlhNhThreeOutlet
            }
        }
    }

    // Set Standard Value Calculation
    override suspend fun setOneKgBodStandard(oneKgBodStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "oneKgBodStandard")] = oneKgBodStandard
            }
        }
    }

    override suspend fun setOneKgCodStandard(oneKgCodStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "oneKgCodStandard")] = oneKgCodStandard
            }
        }
    }

    override suspend fun setOneKgNhThreeStandard(oneKgNhThreeStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "oneKgNhThreeStandard")] =
                    oneKgNhThreeStandard
            }
        }
    }

    override suspend fun setPercentOxygenInAirStandard(percentOxygenInAirStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "percentOxygenInAirStandard")] =
                    percentOxygenInAirStandard
            }
        }
    }

    override suspend fun setDensityOfOxygenStandard(densityOfOxygenStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "densityOfOxygenStandard")] =
                    densityOfOxygenStandard
            }
        }
    }

    override suspend fun setDiffuserCapacityTwelveInchStandard(diffuserCapacityTwelveInchStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "diffuserCapacityTwelveInchStandard")] =
                    diffuserCapacityTwelveInchStandard
            }
        }
    }

    override suspend fun setDiffuserCapacityNineInchStandard(diffuserCapacityNineInchStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "diffuserCapacityNineInchStandard")] =
                    diffuserCapacityNineInchStandard
            }
        }
    }

    override suspend fun setPercentOxygenTransferEfficiencyStandard(
        percentOxygenTransferEfficiencyStandard: Double,
    ) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "percentOxygenTransferEfficiencyStandard")] =
                    percentOxygenTransferEfficiencyStandard
            }
        }
    }

    override suspend fun setEffectiveSurfaceAreaDiffTwelveInchStandard(
        effectiveSurfaceAreaDiffTwelveInchStandard: Double,
    ) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "effectiveSurfaceAreaDiffTwelveInchStandard")] =
                    effectiveSurfaceAreaDiffTwelveInchStandard
            }
        }
    }

    override suspend fun setEffectiveSurfaceAreaDiffNineInchStandard(
        effectiveSurfaceAreaDiffNineInchStandard: Double,
    ) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "effectiveSurfaceAreaDiffNineInchStandard")] =
                    effectiveSurfaceAreaDiffNineInchStandard
            }
        }
    }

    override suspend fun setMinimumMixingAreaStandard(minimumMixingAreaStandard: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "minimumMixingAreaStandard")] =
                    minimumMixingAreaStandard
            }
        }
    }

    // Get DLH Standard Value
    override suspend fun getDlhBodOutlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "dlhBodOutlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDlhCodOutlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "dlhCodOutlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDlhNhThreeOutlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "dlhNhThreeOutlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Get Standard Value Calculation
    override suspend fun getOneKgBodStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "oneKgBodStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getOneKgCodStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "oneKgCodStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getOneKgNhThreeStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "oneKgNhThreeStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getPercentOxygenInAirStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "percentOxygenInAirStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDensityOfOxygenStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "densityOfOxygenStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDiffuserCapacityTwelveInchStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "diffuserCapacityTwelveInchStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDiffuserCapacityNineInchStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "diffuserCapacityNineInchStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getPercentOxygenTransferEfficiencyStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "percentOxygenTransferEfficiencyStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getEffectiveSurfaceAreaDiffTwelveInchStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "effectiveSurfaceAreaDiffTwelveInchStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getEffectiveSurfaceAreaDiffNineInchStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "effectiveSurfaceAreaDiffNineInchStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getMinimumMixingAreaStandard(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "minimumMixingAreaStandard")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Input Parameter
    override suspend fun setInputBodInlet(inputBodInlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputBodInlet")] = inputBodInlet
            }
        }
    }

    override suspend fun setInputBodOutlet(inputBodOutlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputBodOutlet")] = inputBodOutlet
            }
        }
    }

    override suspend fun setInputCodInlet(inputCodInlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputCodInlet")] = inputCodInlet
            }
        }
    }

    override suspend fun setInputCodOutlet(inputCodOutlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputCodOutlet")] = inputCodOutlet
            }
        }
    }

    override suspend fun setInputNhThreeInlet(inputNhThreeInlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputNhThreeInlet")] = inputNhThreeInlet
            }
        }
    }

    override suspend fun setInputNhThreeOutlet(inputNhThreeOutlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputNhThreeOutlet")] = inputNhThreeOutlet
            }
        }
    }

    override suspend fun setInputFlowRate(inputFlowRate: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputFlowRate")] = inputFlowRate
            }
        }
    }

    override suspend fun setInputPeakHourAssumption(inputPeakHourAssumption: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputPeakHourAssumption")] =
                    inputPeakHourAssumption
            }
        }
    }

    override suspend fun setInputWaterDepthAboveDiffuser(inputWaterDepthAboveDiffuser: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "inputWaterDepthAboveDiffuser")] =
                    inputWaterDepthAboveDiffuser
            }
        }
    }

    // Get Input Parameter
    override suspend fun getInputBodInlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputBodInlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputBodOutlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputBodOutlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputCodInlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputCodInlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputCodOutlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputCodOutlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputNhThreeInlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputNhThreeInlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputNhThreeOutlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputNhThreeOutlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputFlowRate(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputFlowRate")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputPeakHourAssumption(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputPeakHourAssumption")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getInputWaterDepthAboveDiffuser(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "inputWaterDepthAboveDiffuser")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Calculation Parameter
    override suspend fun setPercentDiffuserDepthEfficiency(percentDiffuserDepthEfficiency: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "percentDiffuserDepthEfficiency")] =
                    percentDiffuserDepthEfficiency
            }
        }
    }

    // Get Calculation Parameter
    override suspend fun getPercentDiffuserDepthEfficiency(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "percentDiffuserDepthEfficiency")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Total influent Loading BOD, COD, & NH3 per hour
    override suspend fun setTotalBodInlet(totalBodInlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "totalBodInlet")] = totalBodInlet
            }
        }
    }

    override suspend fun setTotalCodInlet(totalCodInlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "totalCodInlet")] = totalCodInlet
            }
        }
    }

    override suspend fun setTotalNhThreeInlet(totalNhThreeInlet: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "totalNhThreeInlet")] = totalNhThreeInlet
            }
        }
    }

    // Get Total influent Loading BOD, COD, & NH3 per hour
    override suspend fun getTotalBodInlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "totalBodInlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTotalCodInlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "totalCodInlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTotalNhThreeInlet(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "totalNhThreeInlet")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Air Requirement Per Minute and Per Hour
    override suspend fun setForBodPerMinute(forBodPerMinute: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "forBodPerMinute")] = forBodPerMinute
            }
        }
    }

    override suspend fun setForCodPerMinute(forCodPerMinute: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "forCodPerMinute")] = forCodPerMinute
            }
        }
    }

    override suspend fun setForNhThreePerMinute(forNhThreePerMinute: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "forNhThreePerMinute")] =
                    forNhThreePerMinute
            }
        }
    }

    override suspend fun setTotalAirRequirementPerMinute(totalAirRequirementPerMinute: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "totalAirRequirementPerMinute")] =
                    totalAirRequirementPerMinute
            }
        }
    }

    override suspend fun setForBodPerHour(forBodPerHour: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "forBodPerHour")] = forBodPerHour
            }
        }
    }

    override suspend fun setForCodPerHour(forCodPerHour: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "forCodPerHour")] = forCodPerHour
            }
        }
    }

    override suspend fun setForNhThreePerHour(forNhThreePerHour: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "forNhThreePerHour")] = forNhThreePerHour
            }
        }
    }

    override suspend fun setTotalAirRequirementPerHour(totalAirRequirementPerHour: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "totalAirRequirementPerHour")] =
                    totalAirRequirementPerHour
            }
        }
    }

    // Get Air Requirement Per Minute and Per Hour
    override suspend fun getForBodPerMinute(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "forBodPerMinute")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getForCodPerMinute(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "forCodPerMinute")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getForNhThreePerMinute(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "forNhThreePerMinute")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTotalAirRequirementPerMinute(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "totalAirRequirementPerMinute")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getForBodPerHour(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "forBodPerHour")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getForCodPerHour(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "forCodPerHour")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getForNhThreePerHour(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "forNhThreePerHour")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTotalAirRequirementPerHour(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "totalAirRequirementPerHour")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Diffuser Requirement
    override suspend fun setDiffuserTwelveInchUnit(diffuserTwelveUnit: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "diffuserTwelveUnit")] = diffuserTwelveUnit
            }
        }
    }

    override suspend fun setDiffuserTwelveInchUnitRoundUp(diffuserTwelveUnitRoundUp: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "diffuserTwelveUnitRoundUp")] =
                    diffuserTwelveUnitRoundUp
            }
        }
    }

    override suspend fun setDiffuserNineInchUnit(diffuserNineUnit: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "diffuserNineUnit")] = diffuserNineUnit
            }
        }
    }

    override suspend fun setDiffuserNineInchUnitRoundUp(diffuserNineUnitRoundUp: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "diffuserNineUnitRoundUp")] =
                    diffuserNineUnitRoundUp
            }
        }
    }

    // Get Diffuser Requirement
    override suspend fun getDiffuserTwelveInchUnit(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "diffuserTwelveUnit")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDiffuserTwelveInchUnitRoundUp(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "diffuserTwelveUnitRoundUp")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDiffuserNineInchUnit(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "diffuserNineUnit")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDiffuserNineInchUnitRoundUp(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "diffuserNineUnitRoundUp")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Blower Capacity Requirement Per Minute and Per Hour
    override suspend fun setBlowerCapacityRequirementPerMinute(blowerCapacityRequirementPerMinute: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "blowerCapacityRequirementPerMinute")] =
                    blowerCapacityRequirementPerMinute
            }
        }
    }

    override suspend fun setBlowerCapacityRequirementPerHour(blowerCapacityRequirementPerHour: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "blowerCapacityRequirementPerHour")] =
                    blowerCapacityRequirementPerHour
            }
        }
    }

    // Get Blower Capacity Requirement Per Minute and Per Hour
    override suspend fun getBlowerCapacityRequirementPerMinute(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "blowerCapacityRequirementPerMinute")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getBlowerCapacityRequirementPerHour(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "blowerCapacityRequirementPerHour")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Area & Volume Requirement
    override suspend fun setAerationChamberAreaWithDiffuserTwelveInch(
        aerationChamberAreaWithDiffuserTwelveInch: Double,
    ) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "aerationChamberAreaWithDiffuserTwelveInch")] =
                    aerationChamberAreaWithDiffuserTwelveInch
            }
        }
    }

    override suspend fun setAerationChamberVolumeWithDiffuserTwelveInch(
        aerationChamberVolumeWithDiffuserTwelveInch: Double,
    ) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "aerationChamberVolumeWithDiffuserTwelveInch")] =
                    aerationChamberVolumeWithDiffuserTwelveInch
            }
        }
    }

    // Get Area & Volume Requirement
    override suspend fun getAerationChamberAreaWithDiffuserTwelveInch(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "aerationChamberAreaWithDiffuserTwelveInch")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getAerationChamberVolumeWithDiffuserTwelveInch(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "aerationChamberVolumeWithDiffuserTwelveInch")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Standard System BW and GW Separated
    // Set Standard System Tab Area STP Requirement
    override suspend fun setRetentionTimeAnaerob(retentionTimeAnaerob: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeAnaerob")] =
                    retentionTimeAnaerob
            }
        }
    }

    override suspend fun setPercentBwTowardsTotalFlowForHotel(percentBwTowardsTotalFlowForHotel: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "percentBwTowardsTotalFlowForHotel")] =
                    percentBwTowardsTotalFlowForHotel
            }
        }
    }

    override suspend fun setPercentBwTowardsTotalFlowForIndustry(
        percentBwTowardsTotalFlowForIndustry: Double,
    ) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "percentBwTowardsTotalFlowForIndustry")] =
                    percentBwTowardsTotalFlowForIndustry
            }
        }
    }

    override suspend fun setTargetBodReductionFromAnaerob(targetBodReductionFromAnaerob: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetBodReductionFromAnaerob")] =
                    targetBodReductionFromAnaerob
            }
        }
    }

    override suspend fun setRetentionTimeAeration(retentionTimeAeration: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeAeration")] =
                    retentionTimeAeration
            }
        }
    }

    override suspend fun setTargetBodReductionFromAeration(targetBodReductionFromAeration: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetBodReductionFromAeration")] =
                    targetBodReductionFromAeration
            }
        }
    }

    override suspend fun setTargetCodReductionFromAeration(targetCodReductionFromAeration: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetCodReductionFromAeration")] =
                    targetCodReductionFromAeration
            }
        }
    }

    override suspend fun setTargetNhThreeReductionFromAeration(targetNhThreeReductionFromAeration: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetNhThreeReductionFromAeration")] =
                    targetNhThreeReductionFromAeration
            }
        }
    }

    override suspend fun setRetentionTimeGreaseTrap(retentionTimeGreaseTrap: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeGreaseTrap")] =
                    retentionTimeGreaseTrap
            }
        }
    }

    override suspend fun setTotalChamberGreaseTrap(totalChamberGreaseTrap: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "totalChamberGreaseTrap")] =
                    totalChamberGreaseTrap
            }
        }
    }

    override suspend fun setTargetFogRemovalFromGreaseTrap(targetFogRemovalFromGreaseTrap: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetFogRemovalFromGreaseTrap")] =
                    targetFogRemovalFromGreaseTrap
            }
        }
    }

    override suspend fun setRetentionTimeSedimentation(retentionTimeSedimentation: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeSedimentation")] =
                    retentionTimeSedimentation
            }
        }
    }

    override suspend fun setTargetTssRemovalFromGreaseTrap(targetTssRemovalFromGreaseTrap: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetTssRemovalFromGreaseTrap")] =
                    targetTssRemovalFromGreaseTrap
            }
        }
    }

    override suspend fun setRetentionTimeChlorination(retentionTimeChlorination: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeChlorination")] =
                    retentionTimeChlorination
            }
        }
    }

    override suspend fun setTargetEcoliRemovalFromChlorination(targetEcoliRemovalFromChlorination: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "targetEcoliRemovalFromChlorination")] =
                    targetEcoliRemovalFromChlorination
            }
        }
    }

    // Get Standard System Tab Area STP Requirement
    override suspend fun getRetentionTimeAnaerob(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeAnaerob")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getPercentBwTowardsTotalFlowForHotel(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "percentBwTowardsTotalFlowForHotel")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getPercentBwTowardsTotalFlowForIndustry(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "percentBwTowardsTotalFlowForIndustry")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetBodReductionFromAnaerob(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetBodReductionFromAnaerob")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getRetentionTimeAeration(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeAeration")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetBodReductionFromAeration(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetBodReductionFromAeration")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetCodReductionFromAeration(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetCodReductionFromAeration")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetNhThreeReductionFromAeration(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetNhThreeReductionFromAeration")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getRetentionTimeGreaseTrap(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeGreaseTrap")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTotalChamberGreaseTrap(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "totalChamberGreaseTrap")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetFogRemovalFromGreaseTrap(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetFogRemovalFromGreaseTrap")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getRetentionTimeSedimentation(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeSedimentation")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetTssRemovalFromGreaseTrap(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetTssRemovalFromGreaseTrap")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getRetentionTimeChlorination(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "retentionTimeChlorination")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getTargetEcoliRemovalFromChlorination(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "targetEcoliRemovalFromChlorination")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Input Parameters for Area STP Requirement
    override suspend fun setBodOutletAnaerob(bodOutletAnaerob: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "bodOutletAnaerob")] =
                    bodOutletAnaerob
            }
        }
    }

    override suspend fun setBodOutletAerob(bodOutletAerob: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "bodOutletAerob")] =
                    bodOutletAerob
            }
        }
    }

    override suspend fun setCodOutletAerob(codOutletAerob: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "codOutletAerob")] =
                    codOutletAerob
            }
        }
    }

    override suspend fun setNhThreeAerob(nhThreeAerob: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "nhThreeAerob")] =
                    nhThreeAerob
            }
        }
    }

    // Get Input Parameters for Area STP Requirement
    override suspend fun getBodOutletAnaerob(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "bodOutletAnaerob")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getBodOutletAerob(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "bodOutletAerob")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getCodOutletAerob(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "codOutletAerob")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getNhThreeAerob(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "nhThreeAerob")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Anaerob Room Requirement
    override suspend fun setVolumeAnaerobRoom(volumeAnaerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeAnaerobRoom")] =
                    volumeAnaerobRoom
            }
        }
    }

    override suspend fun setLengthAnaerobRoom(lengthAnaerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthAnaerobRoom")] =
                    lengthAnaerobRoom
            }
        }
    }

    override suspend fun setWidthAnaerobRoom(widthAnaerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthAnaerobRoom")] =
                    widthAnaerobRoom
            }
        }
    }

    override suspend fun setDepthAnaerobRoom(depthAnaerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthAnaerobRoom")] =
                    depthAnaerobRoom
            }
        }
    }

    // Get Anaerob Room Requirement
    override suspend fun getVolumeAnaerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeAnaerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthAnaerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthAnaerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthAnaerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthAnaerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthAnaerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthAnaerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Grease Trap Room Requirement
    override suspend fun setVolumeGreaseTrapRoom(volumeGreaseTrapRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeGreaseTrapRoom")] =
                    volumeGreaseTrapRoom
            }
        }
    }

    override suspend fun setLengthGreaseTrapRoom(lengthGreaseTrapRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthGreaseTrapRoom")] =
                    lengthGreaseTrapRoom
            }
        }
    }

    override suspend fun setWidthGreaseTrapRoom(widthGreaseTrapRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthGreaseTrapRoom")] =
                    widthGreaseTrapRoom
            }
        }
    }

    override suspend fun setDepthGreaseTrapRoom(depthGreaseTrapRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthGreaseTrapRoom")] =
                    depthGreaseTrapRoom
            }
        }
    }

    // Get Grease Trap Room Requirement
    override suspend fun getVolumeGreaseTrapRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeGreaseTrapRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthGreaseTrapRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthGreaseTrapRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthGreaseTrapRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthGreaseTrapRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthGreaseTrapRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthGreaseTrapRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Aerob Room Requirement
    override suspend fun setVolumeAerobRoom(volumeAerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeAerobRoom")] =
                    volumeAerobRoom
            }
        }
    }

    override suspend fun setLengthAerobRoom(lengthAerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthAerobRoom")] =
                    lengthAerobRoom
            }
        }
    }

    override suspend fun setWidthAerobRoom(widthAerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthAerobRoom")] =
                    widthAerobRoom
            }
        }
    }

    override suspend fun setDepthAerobRoom(depthAerobRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthAerobRoom")] =
                    depthAerobRoom
            }
        }
    }

    // Get Aerob Room Requirement
    override suspend fun getVolumeAerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeAerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthAerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthAerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthAerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthAerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthAerobRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthAerobRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Sedimentation Room Requirement
    override suspend fun setVolumeSedimentationRoom(volumeSedimentationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeSedimentationRoom")] =
                    volumeSedimentationRoom
            }
        }
    }

    override suspend fun setLengthSedimentationRoom(lengthSedimentationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthSedimentationRoom")] =
                    lengthSedimentationRoom
            }
        }
    }

    override suspend fun setWidthSedimentationRoom(widthSedimentationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthSedimentationRoom")] =
                    widthSedimentationRoom
            }
        }
    }

    override suspend fun setDepthSedimentationRoom(depthSedimentationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthSedimentationRoom")] =
                    depthSedimentationRoom
            }
        }
    }

    // Get Sedimentation Room Requirement
    override suspend fun getVolumeSedimentationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeSedimentationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthSedimentationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthSedimentationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthSedimentationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthSedimentationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthSedimentationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthSedimentationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Chlorination Room Requirement
    override suspend fun setVolumeChlorinationRoom(volumeChlorinationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeChlorinationRoom")] =
                    volumeChlorinationRoom
            }
        }
    }

    override suspend fun setLengthChlorinationRoom(lengthChlorinationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthChlorinationRoom")] =
                    lengthChlorinationRoom
            }
        }
    }

    override suspend fun setWidthChlorinationRoom(widthChlorinationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthChlorinationRoom")] =
                    widthChlorinationRoom
            }
        }
    }

    override suspend fun setDepthChlorinationRoom(depthChlorinationRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthChlorinationRoom")] =
                    depthChlorinationRoom
            }
        }
    }

    // Get Chlorination Room Requirement
    override suspend fun getVolumeChlorinationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeChlorinationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthChlorinationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthChlorinationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthChlorinationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthChlorinationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthChlorinationRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthChlorinationRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Filtration Tank Room Requirement
    override suspend fun setVolumeFiltrationTankRoom(volumeFiltrationTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeFiltrationTankRoom")] =
                    volumeFiltrationTankRoom
            }
        }
    }

    override suspend fun setLengthFiltrationTankRoom(lengthFiltrationTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthFiltrationTankRoom")] =
                    lengthFiltrationTankRoom
            }
        }
    }

    override suspend fun setWidthFiltrationTankRoom(widthFiltrationTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthFiltrationTankRoom")] =
                    widthFiltrationTankRoom
            }
        }
    }

    override suspend fun setDepthFiltrationTankRoom(depthFiltrationTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthFiltrationTankRoom")] =
                    depthFiltrationTankRoom
            }
        }
    }

    // Get Filtration Tank Room Requirement
    override suspend fun getVolumeFiltrationTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeFiltrationTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthFiltrationTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthFiltrationTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthFiltrationTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthFiltrationTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthFiltrationTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthFiltrationTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    // Set Recycle Tank Room Requirement
    override suspend fun setVolumeRecycleTankRoom(volumeRecycleTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "volumeRecycleTankRoom")] =
                    volumeRecycleTankRoom
            }
        }
    }

    override suspend fun setLengthRecycleTankRoom(lengthRecycleTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "lengthRecycleTankRoom")] =
                    lengthRecycleTankRoom
            }
        }
    }

    override suspend fun setWidthRecycleTankRoom(widthRecycleTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "widthRecycleTankRoom")] =
                    widthRecycleTankRoom
            }
        }
    }

    override suspend fun setDepthRecycleTankRoom(depthRecycleTankRoom: Double) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[doublePreferencesKey(name = "depthRecycleTankRoom")] =
                    depthRecycleTankRoom
            }
        }
    }

    // Get Recycle Tank Room Requirement
    override suspend fun getVolumeRecycleTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "volumeRecycleTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getLengthRecycleTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "lengthRecycleTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getWidthRecycleTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "widthRecycleTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun getDepthRecycleTankRoom(): Result<Double> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[doublePreferencesKey(name = "depthRecycleTankRoom")]
            }
            val value = flow.firstOrNull() ?: 0.0
            value
        }
    }

    override suspend fun setAuthenticationStatus(authenticationStatus: Boolean) {
        Result.runCatching {
            dataStorePreferences.edit { preferences ->
                preferences[booleanPreferencesKey(name = "authenticationStatus")] =
                    authenticationStatus
            }
        }
    }

    override suspend fun getAuthenticationStatus(): Result<Boolean> {
        return Result.runCatching {
            val flow = dataStorePreferences.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[booleanPreferencesKey(name = "authenticationStatus")]
            }
            val value = flow.firstOrNull() ?: false
            value
        }
    }
}