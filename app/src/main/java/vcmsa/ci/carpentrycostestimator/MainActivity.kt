package vcmsa.ci.carpentrycostestimator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var radioGroupTasks:RadioGroup
    private lateinit var cbWood: CheckBox
    private lateinit var cbNails: CheckBox
    private lateinit var cbHinges: CheckBox
    private lateinit var etHours: EditText
    private lateinit var btnCalculateTotal: Button
    private lateinit var tvTotalEstimation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        radioGroupTasks = findViewById(R.id.radioGroupTasks)
        cbWood = findViewById(R.id.cbWood)
        cbNails = findViewById(R.id.cbNails)
        cbHinges = findViewById(R.id.cbHinges)
        etHours = findViewById(R.id.etHours)
        btnCalculateTotal = findViewById(R.id.btnCalculateTotal)
        tvTotalEstimation = findViewById(R.id.tvTotalEstimation)

        btnCalculateTotal.setOnClickListener {
            CalculateTotal()

        }
    }
    private fun CalculateTotal(){
        val hoursStr = etHours.text.toString()
           if (hoursStr.isEmpty()||hoursStr.toIntOrNull()==null ||hoursStr.toInt()<= 0) {
               Toast.makeText(this, "please enter a valid number of hours",Toast.LENGTH_SHORT).show()
               return
           }
        if (radioGroupTasks.checkedRadioButtonId==-1) {
            Toast.makeText(this,"please select a task",Toast.LENGTH_SHORT).show()
            return
        }
        val hours = hoursStr.toInt()
        var materialCost = 0
        if (cbWood.isChecked)materialCost += 600
        if (cbNails.isChecked)materialCost += 50
        if (cbHinges.isChecked)materialCost += 40

        if (materialCost == 0) {
            Toast.makeText(this,"please select at least one material",Toast.LENGTH_SHORT).show()
            return
        }
        val totalCost = materialCost * hours
        tvTotalEstimation.text = "Total Estimated Cost: R$totalCost.00"
    }

}