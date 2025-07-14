package com.example.firebasefirestore.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.firebasefirestore.data.Note
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NotesApp() {
    var noteText by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf<List<Note>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isAddingNote by remember { mutableStateOf(false) }

    val firestore = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {
        firestore.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                notes = result.documents.mapNotNull { doc ->
//                    //Mappingg each Firestore document to a Note object and stores it in notes.
                    Note(id = doc.id, text = doc.getString("text") ?: "")
                }
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = false
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            label = { Text("Write a note...") },
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                if (noteText.isNotBlank() && !isAddingNote) {
                    isAddingNote = true

                    firestore.collection("notes")
                        .add(mapOf("text" to noteText.trim()))
                        .addOnSuccessListener {
                            noteText = ""
                            isAddingNote = false


                            // Reload notes after adding
                            firestore.collection("notes")
                                .get()
                                .addOnSuccessListener { result ->
                                    notes = result.documents.mapNotNull { doc ->
                                        Note(id = doc.id, text = doc.getString("text") ?: "")
                                    }
                                }
                        }
                        .addOnFailureListener {
                            isAddingNote = false
                        }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = noteText.isNotBlank() && !isAddingNote
        ) {
            if (isAddingNote) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(if (isAddingNote) "Adding..." else "Add Note")
        }


        Text(
            text = "My Notes:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )


        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Loading notes...",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        } else if (notes.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "No notes yet. Add your first note above!",
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notes) { note ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = note.text,
                                modifier = Modifier.weight(1f)
                            )

                            TextButton(
                                onClick = {
                                    firestore.collection("notes")
                                        .document(note.id)
                                        .delete()
                                        .addOnSuccessListener {

                                            firestore.collection("notes")
                                                .get()
                                                .addOnSuccessListener { result ->
                                                    notes = result.documents.mapNotNull { doc ->
                                                        Note(id = doc.id, text = doc.getString("text") ?: "")
                                                    }
                                                }
                                        }
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}