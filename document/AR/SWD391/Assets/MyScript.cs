using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;

public class MyScript : MonoBehaviour, ITrackableEventHandler
{
    private Sprite girl;
    private TrackableBehaviour trackableBehaviour;
    private bool isRendered = false;
    private AudioSource source;

    public void OnTrackableStateChanged(TrackableBehaviour.Status previousStatus, TrackableBehaviour.Status newStatus)
    {
        if (newStatus == TrackableBehaviour.Status.DETECTED ||
                  newStatus == TrackableBehaviour.Status.TRACKED ||
                  newStatus == TrackableBehaviour.Status.EXTENDED_TRACKED)
        {
            OnTrackingFound();
        }
        else
        {
            OnTrackingLost();
        }


    }

    private void OnTrackingLost()
    {
        source.Stop();
        Debug.Log("Trackable " + trackableBehaviour.TrackableName + " found> PHONGDVSE61654");
    }

    private void OnTrackingFound()
    {
        source.Play();
        

        Debug.Log("Trackable " + trackableBehaviour.TrackableName + " found> PHONGDVSE61654");
    }

    // Use this for initialization
    void Start () {
        source = GetComponent<AudioSource>();
        girl = GetComponent<Sprite>();
        
        trackableBehaviour = GetComponent<TrackableBehaviour>();
        if (trackableBehaviour)
        {
            trackableBehaviour.RegisterTrackableEventHandler(this);
        }


    }
	
	// Update is called once per frame
	void Update () {
        if (trackableBehaviour.isActiveAndEnabled) {
                
        }


		
	}
}
