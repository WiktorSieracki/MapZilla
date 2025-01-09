import {
  MapContainer,
  TileLayer,
  Marker,
  Popup,
  useMapEvents,
} from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import { useCenterContext } from "../context/center-context";
import { CenterContextProps } from "../interface/centerInterface";

// Fix for default marker icon issue
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl:
    "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png",
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png",
  shadowUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png",
});

function LocationMarker({ setCenter: setCenter }: CenterContextProps) {
  useMapEvents({
    moveend(e) {
      const map = e.target;
      setCenter([map.getCenter().lat, map.getCenter().lng]);
    },
  });
  return null;
}

export const MapBox = () => {
  const { center, setCenter, data, setData } = useCenterContext();

  return (
    <div>
      <div className="w-[600px] h-[600px]">
        <MapContainer
          center={center}
          zoom={13}
          scrollWheelZoom={true}
          style={{ height: "100%", width: "100%" }}
        >
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          {data && console.log(data)}
          <Marker position={center}>
            <Popup>
              A pretty CSS3 popup. <br /> Easily customizable.
            </Popup>
          </Marker>
          <LocationMarker
            center={center}
            setCenter={setCenter}
            data={data}
            setData={setData}
          />
        </MapContainer>
      </div>
      <div>
        Current center coordinates: {center[0]}, {center[1]}
      </div>
      <button
        onClick={() => setCenter([54.39482637467512, 18.574318885803226])}
      >
        Put possition on UG
      </button>
    </div>
  );
};
